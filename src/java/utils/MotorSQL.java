package utils;

import java.sql.ResultSet;

import interfaces.IMotorSQL;


public abstract class MotorSQL implements IMotorSQL  {

	public abstract void connect();

	public abstract int execute(String sql);

	public abstract ResultSet executeQuery(String sql);

	public abstract void disconnect();

}
