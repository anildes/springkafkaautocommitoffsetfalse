package com.softvision.demo.events.handlers;


import com.softvision.demo.events.channels.CustomInput;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

/**
 * Custom Consumer Handler that maps to a binding name other than "input" via CustomInput Interface
 *       bindings:
 *         customInput:
 *           destination: demo.custom.topic
 *           content-type: application/text
 *           group: demoCustomConsumer
 *           binder: kafka1
 */

@EnableBinding(CustomInput.class)
public class CustomChannelDemoMessageHandler  {
    @Autowired
    private BindingServiceProperties props;

    private static final Logger logger = LoggerFactory.getLogger(CustomChannelDemoMessageHandler.class);

    @StreamListener(CustomInput.INPUT_NAME)
    public void process(Message<?> message) throws Exception {



        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);

     try {





            if (acknowledgment != null) {
               // System.out.println(message.getHeaders().get(KafkaHeaders.RECORD_METADATA, RecordMetadata.class).serializedKeySize());
                //System.out.println(recordMetadata.toString());
                int maxAttempts = this.props.getBindingProperties(CustomInput.INPUT_NAME).getConsumer().getMaxAttempts();
                ;
                System.out.println("Binder" + ": " + this.props.getBindingProperties(CustomInput.INPUT_NAME).getBinder());
                //  System.out.println("offset is.."+recordMetadata.hasOffset());
                System.out.println("maxattempts" + ": " + maxAttempts);
                System.out.println(message.getPayload());
                acknowledgment.acknowledge();
                // System.out.println("offset is.."+recordMetadata.offset());
                System.out.println("Acknowledgment provided...............");
                throw new NullPointerException();


            }




    }catch (NullPointerException e1)
     {
        System.out.println(e1.getMessage());
     }
    /* catch(Exception e)


    {

        System.out.println(e.getMessage());

   //System.exit(1);
    }*/


    }
}

