package com.codewithmos.io.config;




import lombok.AllArgsConstructor;
import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.JRecordInterface1;
import net.sf.JRecord.Numeric.ICopybookDialects;
import net.sf.JRecord.def.IO.builders.ICobolIOBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.mapping.RecordFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.function.Function;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;



    @Bean
    public FlatFileItemReader<String> cobolReader() {
        FlatFileItemReader<String> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/customers.txt"));
        itemReader.setName("cobolReader");
        itemReader.setLineMapper( new PassThroughLineMapper());
        System.out.println("running...cobolReager");
        return itemReader;
    }

    //@Bean
    public ICobolIOBuilder CobolReader(){
        ICobolIOBuilder ioBldr
                = JRecordInterface1.COBOL
                .newIOBuilder("src/main/resources/copybook_customers.cpy")
                .setFileOrganization(Constants.IO_TEXT_LINE)
                .setSplitCopybook(CopybookLoader.SPLIT_01_LEVEL)
                .setDialect(ICopybookDialects.FMT_INTEL)
                .setDropCopybookNameFromFields(true);
        return ioBldr;
    }

    @Bean
    public ICobolIOBuilder CobolWriter(){
        ICobolIOBuilder ioBldr
                = JRecordInterface1.COBOL
                .newIOBuilder("src/main/resources/copybook_customers_out.cpy")
                .setFileOrganization(Constants.IO_TEXT_LINE)
                .setSplitCopybook(CopybookLoader.SPLIT_01_LEVEL)
                .setDialect(ICopybookDialects.FMT_INTEL)
                .setDropCopybookNameFromFields(true);
        return ioBldr;
    }


    @Bean
    public CustomerProcessor processor() {
        return new CustomerProcessor();
    }

    @Bean
    public FlatFileItemWriter<AbstractLine> writer()
    {
        //Create writer instance
        FlatFileItemWriter<AbstractLine> writer = new FlatFileItemWriter<>();
        //Set output file location
        writer.setResource(new FileSystemResource("src/main/resources/customers.out"));
        //All job repetitions should "append" to same output file
        writer.setAppendAllowed(true);
        //Name field values sequence based on object properties

        writer.setLineAggregator((line) ->
                line.getFieldValue("Record-Id") +
                 ":" + line.getFieldValue("FirstName") +
                 ":" + line.getFieldValue("Dob") +
                 ":" + line.getFieldValue("LastName") +
                 ":" + line.getFieldValue("Country") +
                 ":" + line.getFieldValue("Gender") +
                 ":" + line.getFieldValue("Email") +
                 ":" + line.getFieldValue("ContactNo")
        );

        return writer;
    }



    @Bean
    public Step step1() {
        return stepBuilderFactory.get("cobol-file-reader-step").<String, AbstractLine>chunk(10)
                .reader( cobolReader())
                .processor( processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("cobolFileJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();

    }


}
