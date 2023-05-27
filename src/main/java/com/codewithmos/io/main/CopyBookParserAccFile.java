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

public class CopyBookParserAccFile {

    public static void main(String[] args) throws IOException, RecordException {
        ICobolIOBuilder ioBldr
                = JRecordInterface1.COBOL
                .newIOBuilder("src/main/resources/POW_GL_Input.cpy")
                .setFileOrganization(Constants.IO_TEXT_LINE)
                .setSplitCopybook(CopybookLoader.SPLIT_01_LEVEL)
                .setDialect(ICopybookDialects.FMT_MAINFRAME)
                .setDropCopybookNameFromFields(true)
                ;

        AbstractLineReader reader = ioBldr.newReader("src/main/resources/ACCOUNTING_FILE_ZA-original");

        // AbstractLine line = reader.read();
        AbstractLine line;
        while ((line = reader.read()) != null ) {
         System.out.println(line.getFieldValue("POW-GL-REC-TYPE"));
         System.out.print(line.getFieldValue("POW-GL-RECORD-SEQ"));
         System.out.print(line.getFieldValue("POW-GL-OPERATION-CODE"));
         System.out.print(line.getFieldValue("POW-GL-OPERATION-SEQ"));
         System.out.print(line.getFieldValue("POW-GL-SETTLEMENT-ACCT-CUTOFF-ID"));
         System.out.print(line.getFieldValue("POW-GL-ENTRY-SEQ"));
         System.out.print(line.getFieldValue("POW-GL-GL-REF"));
         System.out.print(line.getFieldValue("POW-GL-REF-OF-DOCUMENT"));
         System.out.print(line.getFieldValue("POW-GL-ENTRY-LABEL"));
         System.out.print(line.getFieldValue("POW-GL-POSTING-DT"));
         System.out.print(line.getFieldValue("POW-GL-ENTRY-DT"));
         System.out.print(line.getFieldValue("POW-GL-ENTRY-ACCT-NUM"));
         System.out.print(line.getFieldValue("POW-GL-ENTRY-KEY-ACCT"));
         System.out.print(line.getFieldValue("POW-GL-ENTRY-AMT"));
         System.out.print(line.getFieldValue("POW-GL-ENTRY-SIGN"));
         System.out.print(line.getFieldValue("POW-GL-ENTRY-CURRENCY"));
         System.out.print(line.getFieldValue("POW-GL-POSTING-FLAG"));
         System.out.print(line.getFieldValue("POW-GL-BUSINESS-DT"));
        }
        reader.close();
    }
}

