package com.emailClientFiles;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class CustomObjectOutputStream extends ObjectOutputStream{

	
CustomObjectOutputStream() throws IOException{
        super();
    }
CustomObjectOutputStream(OutputStream ot) throws IOException{
        super(ot);
    }
public void writeStreamHeader() throws IOException{
        return;
    }


}
