package cn.szx.cgzb.util;

import cn.szx.cgzb.pagemodel.MyAfferentParametersModel;

public class MyAfferentParametersModelHelper {

	private MyAfferentParametersModel copyRowUsedParamsModel;

	private MyAfferentParametersModel insertRowUsedParamsModel;

	private MyAfferentParametersModel updateRowUsedParamsModel;

	private MyAfferentParametersModel deleteRowUsedParamsModel;

	public MyAfferentParametersModel getCopyRowUsedParamsModel() {
		return copyRowUsedParamsModel;
	}

	public void setCopyRowUsedParamsModel(MyAfferentParametersModel copyRowUsedParamsModel) {
		this.copyRowUsedParamsModel = copyRowUsedParamsModel;
	}

	public MyAfferentParametersModel getInsertRowUsedParamsModel() {
		return insertRowUsedParamsModel;
	}

	public void setInsertRowUsedParamsModel(MyAfferentParametersModel insertRowUsedParamsModel) {
		this.insertRowUsedParamsModel = insertRowUsedParamsModel;
	}

	public MyAfferentParametersModel getUpdateRowUsedParamsModel() {
		return updateRowUsedParamsModel;
	}

	public void setUpdateRowUsedParamsModel(MyAfferentParametersModel updateRowUsedParamsModel) {
		this.updateRowUsedParamsModel = updateRowUsedParamsModel;
	}

	public MyAfferentParametersModel getDeleteRowUsedParamsModel() {
		return deleteRowUsedParamsModel;
	}

	public void setDeleteRowUsedParamsModel(MyAfferentParametersModel deleteRowUsedParamsModel) {
		this.deleteRowUsedParamsModel = deleteRowUsedParamsModel;
	}

	public MyAfferentParametersModelHelper(MyAfferentParametersModel copyRowUsedParamsModel, MyAfferentParametersModel insertRowUsedParamsModel, MyAfferentParametersModel updateRowUsedParamsModel) {
		super();
		this.copyRowUsedParamsModel = copyRowUsedParamsModel;
		this.insertRowUsedParamsModel = insertRowUsedParamsModel;
		this.updateRowUsedParamsModel = updateRowUsedParamsModel;
	}

	public MyAfferentParametersModelHelper(MyAfferentParametersModel copyRowUsedParamsModel, MyAfferentParametersModel updateRowUsedParamsModel) {
		super();
		this.copyRowUsedParamsModel = copyRowUsedParamsModel;
		this.updateRowUsedParamsModel = updateRowUsedParamsModel;
	}

	public MyAfferentParametersModelHelper(MyAfferentParametersModel copyRowUsedParamsModel, MyAfferentParametersModel insertRowUsedParamsModel, MyAfferentParametersModel updateRowUsedParamsModel, MyAfferentParametersModel deleteRowUsedParamsModel) {
		super();
		this.copyRowUsedParamsModel = copyRowUsedParamsModel;
		this.insertRowUsedParamsModel = insertRowUsedParamsModel;
		this.updateRowUsedParamsModel = updateRowUsedParamsModel;
		this.deleteRowUsedParamsModel = deleteRowUsedParamsModel;
	}

}
