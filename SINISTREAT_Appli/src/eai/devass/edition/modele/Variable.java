package eai.devass.edition.modele;

public class Variable extends RapportElement{
	
	private String calculation;
	private String variableExpression;
	
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
