package com.strands.interviews.eventsystem;

import com.strands.interviews.eventsystem.events.SimpleEvent;
import com.strands.interviews.eventsystem.events.SubEvent;
import com.strands.interviews.eventsystem.impl.DefaultEventManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class TasksTest
{
    private EventManager eventManager = new DefaultEventManager();
    
    /*
     * Testing if eventListener is called publishing event with subclass
     */
    @Test
    public void testRegisterSimpleEventAndPublishSubEvent()
    {
        EventListenerMock eventListenerMock = new EventListenerMock(new Class[]{SimpleEvent.class});
        eventManager.registerListener("some.key", eventListenerMock);
        eventManager.publishEvent(new SubEvent(this));
        assertTrue(eventListenerMock.isCalled());
    }
    
    /*
     * Testing if eventListener with subclass is not called publishing event with parent class
     */
    @Test
    public void testRegisterSubEventAndPublishSimpleEvent()
    {
        EventListenerMock eventListenerMock = new EventListenerMock(new Class[]{SubEvent.class});
        eventManager.registerListener("some.key", eventListenerMock);
        eventManager.publishEvent(new SimpleEvent(this));
        assertFalse(eventListenerMock.isCalled());
    }
    
    /*
     * Testing if EventListener is constructed with empty classes and called after publish event 
     */
    @Test
    public void testRegisterEmptyListenertAndPublish()
    {
        EventListenerMock eventListenerMock = new EventListenerMock(new Class[]{});
        eventManager.registerListener("some.key", eventListenerMock);
        eventManager.publishEvent(new SubEvent(this));
        assertTrue(eventListenerMock.isCalled());
    }
    
}
