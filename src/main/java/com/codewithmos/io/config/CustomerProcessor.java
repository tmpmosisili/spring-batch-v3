package com.codewithmos.io.config;



import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.JRecordInterface1;
import net.sf.JRecord.Numeric.ICopybookDialects;
import net.sf.JRecord.def.IO.builders.ICobolIOBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class CustomerProcessor implements ItemProcessor<String, AbstractLine> {


    @Autowired
    public  ICobolIOBuilder iCobolIOBuilder;


    public CustomerProcessor() {
    }

    @Override
    public AbstractLine process(String input) throws Exception {
        ICobolIOBuilder ioBldr
                = JRecordInterface1.COBOL
                .newIOBuilder("src/main/resources/copybook_customers.cpy")
                .setFileOrganization(Constants.IO_TEXT_LINE)
                .setSplitCopybook(CopybookLoader.SPLIT_01_LEVEL)
                .setDialect(ICopybookDialects.FMT_INTEL)
                .setDropCopybookNameFromFields(true);
        ICobolIOBuilder ioBldr1 = iCobolIOBuilder;
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        AbstractLineReader reader = ioBldr1.newReader(new ByteArrayInputStream(bytes));
        AbstractLine line = reader.read();
        System.out.println(line.getFieldValue("Record-Id"));
        System.out.println(line.getFieldValue("FirstName"));
        System.out.println(line.getFieldValue("LastName"));
        reader.close();
        return line;
    }
}
