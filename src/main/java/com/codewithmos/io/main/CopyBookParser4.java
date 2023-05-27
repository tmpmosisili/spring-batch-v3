package com.codewithmos.io.main;

import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Common.RecordException;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.JRecordInterface1;
import net.sf.JRecord.Numeric.ICopybookDialects;
import net.sf.JRecord.def.IO.builders.ICobolIOBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CopyBookParser4 {

    public static void main(String[] args) throws IOException, RecordException {
        ICobolIOBuilder ioBldr
                = JRecordInterface1.COBOL
                .newIOBuilder("src/main/resources/cobol.cpy")
                .setFileOrganization(Constants.IO_TEXT_LINE)
                .setSplitCopybook(CopybookLoader.SPLIT_01_LEVEL)
                .setDialect(ICopybookDialects.FMT_INTEL)
                .setDropCopybookNameFromFields(true);

        String input = "DUMMY   TREQ 000000000000000000000001230130VDINDEEHVODS3NK3KXBC0000000000000                                        STANDIN 01MACHINEINDICATOR                        ZVDAI                  ZADEV  2023/04/11 10:55:54 SYSAI183CHARACTER S";
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        // Creates a `ByteArrayInputStream` from the input buffer
        AbstractLineReader reader = ioBldr.newReader(new ByteArrayInputStream(bytes));

        AbstractLine line = reader.read();
        System.out.println(line.getFieldValue("Y34120D-BFUNC-R-HOGAN-TRAN"));
        System.out.println(line.getFieldValue("Y34120D-STIMULUS-INDIC"));
        System.out.println(line.getFieldValue("Y34120D-STIMULUS"));


    }
}
