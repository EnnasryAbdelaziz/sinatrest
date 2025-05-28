package eai.devass.edition.Utils.beans;

public class VariableBean extends ReportParameterBean{
	private String calculation;
	private String variableExpression;
	public VariableBean(String name, String type, String libelle, String pattern, String textAlignment, String calculation, String variableExpression) {
		super(name, type, libelle, pattern, textAlignment);
		this.calculation = calculation;
		this.variableExpression = variableExpression;
	}
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
	public String getVariableExpression() {
		return variableExpression;
	}
	public void setVariableExpression(String variableExpression) {
		this.variableExpression = variableExpression;
	}
}
