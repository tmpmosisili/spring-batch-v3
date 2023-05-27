package com.codewithmos.io.main;

import net.sf.JRecord.ByteIO.AbstractByteWriter;
import net.sf.JRecord.ByteIO.IByteRecordWriter;
import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Common.RecordException;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.IO.AbstractLineWriter;
import net.sf.JRecord.JRecordInterface1;
import net.sf.JRecord.Numeric.ICopybookDialects;
import net.sf.JRecord.def.IO.builders.ICobolIOBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class CopyBookWriter {

    public static void main(String[] args) throws IOException, RecordException {
        ICobolIOBuilder ioReader
                = JRecordInterface1.COBOL
                .newIOBuilder("src/main/resources/copybook_customers.cpy")
                .setFileOrganization(Constants.IO_TEXT_LINE)
                .setSplitCopybook(CopybookLoader.SPLIT_01_LEVEL)
                .setDialect(ICopybookDialects.FMT_INTEL)
                .setDropCopybookNameFromFields(true);

        ICobolIOBuilder iobWrite
                = JRecordInterface1.COBOL
                .newIOBuilder("src/main/resources/copybook_customers_out.cpy")
                .setFileOrganization(Constants.IO_TEXT_LINE)
                .setSplitCopybook(CopybookLoader.SPLIT_01_LEVEL)
                .setDialect(ICopybookDialects.FMT_INTEL)
                .setDropCopybookNameFromFields(true);

        AbstractLineReader reader = ioReader.newReader("src/main/resources/customers.txt");

       // String input = "DUMMY   TREQ 000000000000000000000001230130VDINDEEHVODS3NK3KXBC0000000000000                                        STANDIN 01MACHINEINDICATOR                        ZVDAI                  ZADEV  2023/04/11 10:55:54 SYSAI183CHARACTER S";
      //  byte[] bytes = input.getBytes(StandardCharsets.UTF_8);

        AbstractLineWriter writer = iobWrite.newWriter( new ByteArrayOutputStream());
        AbstractLine dtar022Line = iobWrite.newLine();

        AbstractLine line;
        while ((line = reader.read()) != null) {
            System.out.println(line.getFieldValue("Record-Id"));
            System.out.println(line.getFieldValue("FirstName"));
            System.out.println(line.getFieldValue("LastName"));
            System.out.println(line.getFieldValue("Email"));
            System.out.println(line.getFieldValue("Gender"));
            System.out.println(line.getFieldValue("ContactNo"));
            System.out.println(line.getFieldValue("Country"));
            System.out.println(line.getFieldValue("Dob"));
        }
        reader.close();
    }
}
