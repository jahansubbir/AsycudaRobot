package com.factory;

import com.models.Egm;

public interface RobotFactory {

	void Automate(Egm egm,String filePath,double cbm,double cWeight,String updateFilePath);
}
