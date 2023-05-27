package com.codewithmos.io.main;

import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Common.RecordException;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.JRecordInterface1;
import net.sf.JRecord.Numeric.ICopybookDialects;
import net.sf.JRecord.def.IO.builders.ICobolIOBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CopyBookParser3 {

    public static void main(String[] args) throws IOException, RecordException {
        ICobolIOBuilder ioBldr
                = JRecordInterface1.COBOL
                .newIOBuilder("src/main/resources/cobol.cpy")
                .setFileOrganization(Constants.IO_TEXT_LINE)
                .setSplitCopybook(CopybookLoader.SPLIT_01_LEVEL)
                .setDialect(ICopybookDialects.FMT_INTEL)
                .setDropCopybookNameFromFields(true)
                ;

        AbstractLineReader reader = ioBldr.newReader("src/main/resources/input.txt");

       // AbstractLine line = reader.read();
        AbstractLine line;
        while ((line = reader.read()) != null ) {
            System.out.println(line.getFieldValue("Y34120D-BFUNC-R-HOGAN-TRAN"));
            System.out.println(line.getFieldValue("Y34120D-STIMULUS-INDIC"));
            System.out.println(line.getFieldValue("Y34120D-STIMULUS"));
        }
        reader.close();
    }
}
