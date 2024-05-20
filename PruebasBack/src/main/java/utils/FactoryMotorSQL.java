package utils;

public class FactoryMotorSQL {
    public static final String POSTGRES = "POSTGRES";

    public static MotorSQL getInstance(String nameMotorSQL){
        MotorSQL motorSQL = null;
        switch (nameMotorSQL){
            case POSTGRES:
                motorSQL = MotorPostgres.getInstance();
                break;
            default:
                break;
        }
        return motorSQL;
    }
}
