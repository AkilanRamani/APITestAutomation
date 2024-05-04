package APITestAutomation;




public class GetColumnConfig {

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnCaption() {
		return columnCaption;
	}

	public void setColumnCaption(String columnCaption) {
		this.columnCaption = columnCaption;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public GetColumnConfig() {
	}

	@Override
	public String toString() {
		return "GetColumnConfig [orderNo=" + orderNo + ", columnName=" + columnName + ", columnCaption=" + columnCaption
				+ ", isEnable=" + isEnable + ", isEdit=" + isEdit + ", max=" + max + ", type=" + type + ", isReadOnly="
				+ isReadOnly + "]";
	}

	int orderNo;
	String columnName;
	String columnCaption;
	boolean isEnable;
	boolean isEdit;
	String max;
	String type;
	boolean isReadOnly;

}
