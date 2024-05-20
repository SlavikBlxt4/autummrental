import express, { Request, Response, NextFunction } from 'express';
import cors from 'cors';
import { Pool } from "pg";
import * as bcrypt from 'bcrypt';
import * as jwt from 'jsonwebtoken';
import dotenv from 'dotenv';
import path from 'path';





const app = express();
app.use(cors());
const PORT = 3000;


app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, 'Intranet')));






const myPool = new Pool({
    user: "postgres",
    host: "cafeteria-bbdd.c9qmqmi86ft5.us-east-1.rds.amazonaws.com",
    database: "postgres",
    password: "postgres",
    port: 5432,
    ssl: {
        rejectUnauthorized: false,
    },
});

app.listen(PORT, ()=> {
    console.log(`Servidor corriendo en http://localhost:${PORT}`);
});




dotenv.config();
const secretKey = process.env.SECRET_KEY;

if (!secretKey) {
  throw new Error('La variable SECRET_KEY no está definida en el archivo .env');
}





interface AuthenticatedRequest extends Request {
    user?: any; // Puedes definir un tipo más específico para user

  }






app.get("/coffee", async (req, res)=>{     
    const {rows} = await myPool.query(   
        "SELECT * FROM cafe ORDER BY id_cafe ASC;;"
        
    );
    res.json(rows);
});

app.get("/category", async (req, res)=>{     
    const {rows} = await myPool.query(      
        "SELECT * FROM categoria;"
        
    );
    res.json(rows);
});




 app.get("/coffee/:id_categoria", async (req, res)=>{    //query para obtener la lista de nombres de todos los cafes que pertenecen a una categoria en concreto
    const {id_categoria} = req.params;
    const {rows} = await myPool.query(        
    "SELECT * FROM cafe WHERE id_categoria= (SELECT id_categoria FROM categoria WHERE id_categoria = $1)", [id_categoria]
 );
    res.json(rows);
});

/*app.get("/favourites/:id_user", async (req, res) => { //query para obtener todos los cafes favoritos de un usuario en especifico
    try {
        const { id_user } = req.params;

        const { rows } = await myPool.query(
            "SELECT * FROM cafe WHERE id_cafe IN (SELECT id_cafe FROM lista WHERE id_user = $1);",
            [id_user]
        );

        res.status(200).json(rows);
    } catch (error) {
        console.error("Error al obtener cafés de la cesta:", error);
        res.status(500).json({ error: "Error interno del servidor" });
    }
});*/

/*app.post("/favourites", async (req, res) => { //metodo para permitir a un usuario agregar un cafe a favoritos
    try {
        const { id_coffee, id_user } = req.body; 

        await myPool.query(
            "INSERT INTO coffee_cart (id_coffee, id_user) VALUES ($1, $2)",
            [id_coffee, id_user]
        );
        res.status(200).json({ message: "Café agregado a la cesta correctamente" });
    } catch (error) {
        console.error("Error al agregar cafés a la cesta:", error);
        res.status(500).json({ error: "Error interno del servidor" });
    }
});*/

/*app.delete("/favourites/:id_coffee/:id_user", async (req, res) => { //metodo para permitir a un usuario eliminar un cafe de la cesta
    try {
        const { id_coffee, id_user } = req.params;
        await myPool.query(
            "DELETE FROM coffee_cart WHERE id_coffee = $1 AND id_user = $2",
            [id_coffee, id_user]
        );
        res.status(200).json({ message: "Café eliminado de la cesta correctamente" });
    } catch (error) {
        console.error("Error al eliminar café de la cesta:", error);
        res.status(500).json({ error: "Error interno del servidor" });
    }
});*/




app.post('/users/login', async (req, res) => { //para logear a los usuarios
    const { email, password } = req.body;

    if (!email || !password) {
        return res.status(400).json({ mensaje: 'Se requiere un email y contraseña' });
    }

    try {
        const result = await myPool.query('SELECT * FROM usuario WHERE email = $1', [email]);
        if (result.rows.length === 0) {
            return res.status(401).json({ mensaje: 'Email o contraseña incorrectos' });
        }

        const usuario = result.rows[0];
        

        const isMatch = await bcrypt.compare(password, usuario.password);
        if (!isMatch) {
            return res.status(401).json({ mensaje: 'Email o contraseña incorrectos' });
        }

        // Create a JWT token if the password is correct
        const token = jwt.sign(
            { userId: usuario.id_usuario, email: usuario.email },
            secretKey,
            { expiresIn: '1h' }
            
        );
        

        return res.status(200).json({ mensaje: 'Login exitoso', token, usuarioId: usuario.id_usuario });
    } catch (error) {
        return res.status(500).json({ mensaje: 'Error al realizar el login', error });
    }
});


async function authenticateToken(req: AuthenticatedRequest, res: Response, next: NextFunction) {
  // Obtener el token JWT del parámetro de consulta 'token'
  const token = typeof req.query.token === 'string' ? req.query.token : null;
  
  if (!token) {
    return res.status(401).json({ message: 'Token no proporcionado' });
  }

  try {
    const actualSecretKey = process.env.SECRET_KEY; // Acceder a la clave secreta desde las variables de entorno
    if (!actualSecretKey) {
      throw new Error('Clave secreta no encontrada en las variables de entorno');
    }


    
    // Verificar el token con la clave secreta
    const decodedToken = jwt.verify(token, actualSecretKey);
    // Guardar el usuario decodificado en el objeto de solicitud para usarlo en las rutas protegidas
    req.user = decodedToken;    
    next(); // Pasar al siguiente middleware
  } catch (error) {
    return res.status(403).json({ message: 'Token inválido' });
  }
  }


  app.get('/private-area', authenticateToken, async (req: AuthenticatedRequest, res) => {
    // Acceder al usuario decodificado desde req.body.user
    const user = req.user;
    console.log(user);
    const token = req.query.token;
    console.log(token);
  
    // Realizar la consulta a la base de datos para verificar el id_rol del usuario
    try {
      const { userId } = user;
      console.log(userId); //indefinido
      const result = await myPool.query('SELECT id_rol FROM usuario WHERE id_usuario = $1', [userId]);
      console.log(result.rows);
  
      if (result.rows.length === 0) {
        return res.status(404).json({ message: 'Usuario no encontrado' });
      }
  
      const { id_rol } = result.rows[0];
      console.log(id_rol);
  
      // Verificar si el id_rol es 2 o 3
      if (+id_rol !== 2 && +id_rol !== 3) {
        return res.status(403).json({ message: 'Acceso no autorizado para este rol de usuario' });
    }

    
    

  
    return res.sendFile(path.join(__dirname, 'Intranet', 'index.html'));
    } catch (error) {
      console.error('Error al obtener el id_rol del usuario:', error);
      res.status(500).json({ message: 'Error interno del servidor' });
    }   
  });

//post para registrar usuarios

app.post('/users', async (req, res) => { //NUNCA meter usuarios a manija en la bbdd, las contraseñas se encriptan
    const { email, password } = req.body;

    try {
        const hashedPassword = await bcrypt.hash(password, 10);
      const queryString = 'INSERT INTO usuario ("email", "password", "id_rol") VALUES($1, $2, 1)';
      const values = [email, hashedPassword];

      await myPool.query(queryString, values);
      res.status(200).json({ message: 'User registered successfully' });
    } catch (error) {
      console.error('Failed to insert user into database:', error);
      res.status(500).json({ error: 'Failed to insert user into database' });
    }
  });


