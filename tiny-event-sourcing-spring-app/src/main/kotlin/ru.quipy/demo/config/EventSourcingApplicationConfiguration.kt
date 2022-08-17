package ru.quipy.demo.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.quipy.core.AggregateRegistry
import ru.quipy.core.EventSourcingServiceFactory
import ru.quipy.demo.projections.AnnotationBasedProjectEventsSubscriber
import ru.quipy.demo.api.ProjectAggregate
import ru.quipy.demo.logic.ProjectAggregateState
import ru.quipy.streams.AggregateEventStreamManager
import ru.quipy.streams.AggregateSubscriptionsManager
import javax.annotation.PostConstruct

@Configuration
class EventSourcingApplicationConfiguration {

    private val logger = LoggerFactory.getLogger(EventSourcingApplicationConfiguration::class.java)

    @Autowired
    private lateinit var subscriptionsManager: AggregateSubscriptionsManager

    @Autowired
    private lateinit var projectEventSubscriber: AnnotationBasedProjectEventsSubscriber

    @Autowired
    private lateinit var eventSourcingServiceFactory: EventSourcingServiceFactory

    @Autowired
    private lateinit var eventStreamManager: AggregateEventStreamManager

    @Autowired
    private lateinit var aggregateRegistry: AggregateRegistry

    @PostConstruct
    fun init() {
        // autoscan enabled see event.sourcing.auto-scan-enabled property
//        aggregateRegistry.register(ProjectAggregate::class, ProjectAggregateState::class) {
//            registerStateTransition(TagCreatedEvent::class, ProjectAggregateState::tagCreatedApply)
//            registerStateTransition(TaskCreatedEvent::class, ProjectAggregateState::taskCreatedApply)
//            registerStateTransition(TagAssignedToTaskEvent::class, ProjectAggregateState::tagAssignedApply)
//        }

        subscriptionsManager.subscribe<ProjectAggregate>(projectEventSubscriber)

        eventStreamManager.maintenance {
            onRecordHandledSuccessfully { streamName, eventName ->
                logger.info("Stream $streamName successfully processed record of $eventName")
            }

            onBatchRead { streamName, batchSize ->
                logger.info("Stream $streamName read batch size: $batchSize")
            }
        }
    }

    @Bean
    fun demoESService() = eventSourcingServiceFactory.getOrCreateService<String, ProjectAggregate, ProjectAggregateState>(ProjectAggregate::class)

}