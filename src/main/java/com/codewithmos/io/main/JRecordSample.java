package com.codewithmos.io.main;

import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Common.RecordException;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.Details.LayoutDetail;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.IO.AbstractLineWriter;
import net.sf.JRecord.IO.CobolIoProvider;
import net.sf.JRecord.IO.LineIOProvider;
import net.sf.JRecord.Numeric.Convert;
import net.sf.JRecord.Numeric.ICopybookDialects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JRecordSample {

    public static void main(String[] args) throws Exception {

        CobolIoProvider ioProvider = CobolIoProvider.getInstance();

        AbstractLineReader reader = ioProvider.getLineReader(
                                Constants.IO_TEXT_LINE,
                                Convert.FMT_INTEL,
                                CopybookLoader.SPLIT_01_LEVEL,
                    "src/main/resources/copybook-v2.cpy",
                        "src/main/resources/input.txt");
        // Read a record
        AbstractLine line;
            while ((line = reader.read()) != null ) {
                System.out.println(line.getFieldValue("Y34120D-BFUNC-R-HOGAN-TRAN"));
                System.out.println(line.getFieldValue("Y34120D-STIMULUS-INDIC"));
                System.out.println(line.getFieldValue("Y34120D-STIMULUS"));
            }
        reader.close();
    }
}