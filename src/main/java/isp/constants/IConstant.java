package isp.constants;

import java.io.File;

public interface IConstant {
    String title = "Input Space Partition Test Generator";
    String lineSeparator = ";";
    String blockSeparator = ",";
    Integer dataLimit = 10000;
    String currentDirectory = System.getProperty("user.dir") + File.separator;
    String dbURL = currentDirectory + "data";
    String dbName = "data.sqlite";

    String inputPrompt = "Characteristic_1  = [Block1, Block2,  ... ]\r" +
            "Characteristic_2 = [Block1, Block2,  ... ]\r" +
            "\r" +"   or   \r\r"+
            "Characteristics_1 = [ block1, block2,  ... ]; Characteristics_2 = [ block1, block2,  ... ]";


    String invalidDataFormatMsg = "Please follow one of the following input formats:\n"+
            "Characteristics_1 = [ block1, block2,  ... ]\n"+
            "Characteristics_2 = [ block1, block2,  ... ]\n\n"+
            "or\n\n"+
            "Characteristics_1 = [ block1, block2,  ... ]; Characteristics_2 = [ block1, block2,  ... ]";

}
