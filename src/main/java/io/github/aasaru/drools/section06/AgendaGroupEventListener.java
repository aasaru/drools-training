package io.github.aasaru.drools.section06;

import org.kie.api.event.rule.*;

import java.io.PrintStream;

public class AgendaGroupEventListener extends DebugAgendaEventListener {

  private PrintStream stream;

  public AgendaGroupEventListener() {
    super();
  }

  public AgendaGroupEventListener(PrintStream stream) {
    super(stream);
    this.stream = stream;
  }

  @Override
  public void matchCreated(MatchCreatedEvent event) {
    stream.println("====> " + event.getMatch().getRule() + " has a match for " + event.getMatch().getObjects());
  }


  @Override
  public void matchCancelled(MatchCancelledEvent event) {
    stream.println("====> " + event.getMatch().getRule() + " match has been cancelled for " + event.getMatch().getObjects());
  }

  @Override
  public void beforeMatchFired(BeforeMatchFiredEvent event) {
    stream.println("====> " + event.getMatch().getRule() + " is going to be fired next for " + event.getMatch().getObjects());
  }

  @Override
  public void afterMatchFired(AfterMatchFiredEvent event) {
    //super.afterMatchFired(event);
  }

  @Override
  public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
    stream.println("==> " + event.getAgendaGroup() + " popped from stack ");
  }

  @Override
  public void agendaGroupPushed(AgendaGroupPushedEvent event) {
    stream.println("==> " + event.getAgendaGroup() + " pushed to stack ");
  }



  @Override
  public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
    //super.beforeRuleFlowGroupActivated(event);
  }

  @Override
  public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
    //super.afterRuleFlowGroupActivated(event);
  }

  @Override
  public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
    //super.beforeRuleFlowGroupDeactivated(event);
  }

  @Override
  public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
    //super.afterRuleFlowGroupDeactivated(event);
  }
}
