package com.myorg;

import lombok.Getter;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.events.targets.SnsTopic;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.EmailSubscription;
import software.constructs.Construct;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

@Getter
public class SnsStack extends Stack {
    private final SnsTopic productEventsTopic;

    public SnsStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public SnsStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        productEventsTopic = SnsTopic.Builder.create(Topic.Builder.create(this, "ProductEventsTopic")
                .topicName("product-events")
                .build())
                .build();

        productEventsTopic.getTopic().addSubscription(EmailSubscription.Builder.create("joanersonc@gmail.com")
                .json(true)
                .build());

        // The code that defines your stack goes here

        // example resource
        // final Queue queue = Queue.Builder.create(this, "CursoAwsCdkQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();
    }
}
