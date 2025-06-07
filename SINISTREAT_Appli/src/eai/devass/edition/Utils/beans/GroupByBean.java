package eai.devass.edition.Utils.beans;

public class GroupByBean {
	private String name;
	private String groupExpression;
	private Integer groupHeaderHeight;
	private Boolean avecColonnesInHeader;
	private FieldBean[] fields;
	private ReportParameterBean[] parameters;
	private Integer groupFooterHeight;
	private VariableBean[] footerVariables;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupExpression() {
		return groupExpression;
	}

	public void setGroupExpression(String groupExpression) {
		this.groupExpression = groupExpression;
	}

	public Integer getGroupHeaderHeight() {
		return groupHeaderHeight;
	}

	public void setGroupHeaderHeight(Integer groupHeaderHeight) {
		this.groupHeaderHeight = groupHeaderHeight;
	}

	public Boolean getAvecColonnesInHeader() {
		return avecColonnesInHeader;
	}

	public void setAvecColonnesInHeader(Boolean avecColonnesInHeader) {
		this.avecColonnesInHeader = avecColonnesInHeader;
	}

	public FieldBean[] getFields() {
		return fields;
	}

	public void setFields(FieldBean[] fields) {
		this.fields = (FieldBean[]) fields.clone();
	}

	public ReportParameterBean[] getParameters() {
		return parameters;
	}

	public void setParameters(ReportParameterBean[] parameters) {

		ReportParameterBean[] copyParameters = null;
		if (parameters != null) {
			copyParameters = parameters.clone();
		}

		this.parameters = copyParameters;
	}

	public Integer getGroupFooterHeight() {
		return groupFooterHeight;
	}

	public void setGroupFooterHeight(Integer groupFooterHeight) {
		this.groupFooterHeight = groupFooterHeight;
	}

	public VariableBean[] getFooterVariables() {
		return footerVariables;
	}

	public void setFooterVariables(VariableBean[] footerVariables) {

		VariableBean[] copyFooterVariables = null;
		if (footerVariables != null) {
			copyFooterVariables = footerVariables.clone();
		}
		
		this.footerVariables = copyFooterVariables;
	}
}
