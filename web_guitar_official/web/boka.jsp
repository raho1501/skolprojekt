<%-- 
    Document   : boka
    Created on : 2017-okt-06, 22:28:11
    Author     : markus
--%>

<%@page import="beans.CustomerManagedBean"%>
<%@page import="beans.Customer"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%
        FacesContext context = FacesContext.getCurrentInstance();
        CustomerManagedBean customerManagedBean = (CustomerManagedBean)context.getELContext().getELResolver().getValue(context.getELContext(), null, "customerManagedBean");
        
        for(Customer customer : customerManagedBean.getCustomers())
        {
                
        }
%>

<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Boka tid</title>
        <meta name="description" content="" />
        <meta name="keywords" content="" />

        <script src="resources/js/jquery.min.js"></script>
        <script src="resources/js/skel.min.js"></script>
        <script src="resources/js/skel-layers.min.js"></script>
        <script src="resources/js/init.js"></script>


        <link rel="stylesheet" href="resources/css/style.css" />
        <link rel='stylesheet' href='resources/fullcalendar/fullcalendar.css' />
        <script src='resources/js/moment.min.js'></script>
        <script src='resources/fullcalendar/fullcalendar.js'></script>
        <script src='resources/fullcalendar/locale/sv.js'></script>
        <link rel='stylesheet' href='resources/jquery-ui/jquery-ui.css' />
        <script src="resources/jquery-ui/jquery-ui.js"></script>
    </head>
    <body id="top">
        <!-- Header -->
        <header id="header" class="skel-layers-fixed">
            <h1><a href="#">ANDERS GITARRVERKSTAD</a></h1>
            <nav id="nav">
                <ul>
                    <li><a href="index.html">START</a></li>
                    <li><a href="shop.html">SHOP</a></li>
                    <li><a href="boka.html">BOKA TID</a></li>
                    <li><a href="kontakt.html">KONTAKT</a></li>
                </ul>
            </nav>
        </header>
        <!-- Main -->
        <div>
            <div id="boka1" style ="margin-bottom:25px">
                <div style="margin: 10px" id="calendar"></div>
            </div>
        </div>
        <!-- Footer -->
        <footer id="footer">
            <div class="container">
                <div class="row double">
                </div>
            </div>
        </footer>
        <div id="dialog" title="Bekräfta bokning" style="display:none">
            <form>
                <input type="text" placeholder="Namn"><br>
                <input type="text" placeholder="Email"><br>
                <input type="text" placeholder="Telefon"><br>
                <input type="textarea" placeholder="Beskrivning"><br>
                <input type="file" multiple>
                <input id="reserve-button" type="button" value="BOKA" style="width:170px; margin-left:10px" >
            </form>
        </div>
        <script>
        $(document).ready(function() {
            // page is now ready, initialize the calendar...
            var events = [];
            var currentEvent;
            
            function insertEvent(name, date, startTime, endTime)
            {
                var startValue = moment(date + " " + startTime, "MM/DD/YYYY HH:mm");
                var endValue = moment(date + " " + endTime, "MM/DD/YYYY HH:mm");
                
                var newid = 0;
                if(events.length > 0)
                {
                    newid = events[events.length-1].id + 1;
                }
                var event = {
                    id: newid,
                    title: name,
                    start: startValue,
                    end: endValue,
                    allDay: false,
                    editable: false,
                    color: 'red'};
                events.push(event);
            }

            function displayEvents()
            {
                for(var i = 0; i < events.length; i++)
                {
                    $('#calendar').fullCalendar('renderEvent', events[i]);
                }
            }

            $('#reserve-button').click(function() {
                currentEvent.color = 'red';
                events.push(currentEvent);
                $('#calendar').fullCalendar('refetchEvents');
                displayEvents();
                $('#dialog').dialog("close");
                currentEvent = (function () { return; });
            });

            $('#calendar').fullCalendar({
                    // put your options and callbacks here
                eventClick: function(calEvent, jsEvent, view) {
                    if(currentEvent == undefined)
                    {
                        return;
                    }
                    if(calEvent.id == currentEvent.id)
                    {
                        $(function() {
                            $("#dialog").dialog({
                                width: "auto",
                                resizable: false,
                                modal: false
                            });
                        });
                        // change the border color just for fun
                        $(this).css('border-color', 'red');
                    }
                },
                defaultView: 'agendaWeek',
                weekNumbers: true,
                allDaySlot: false,
                minTime: "10:00:00",
                maxTime: "18:00:00",
                snapDuration: "00:30:00",
                height: "auto",
                selectable: true,
                weekends: false,
                selectOverlap: false,
                viewRender: function() {
                    displayEvents();
                },
                select: function(start, end) {
                    if(!Number.isInteger(start.time().asHours()))
                    {
                        start.minute(0);
                    }
                    var currentDate = moment();
                    if(start.week() < currentDate.week())
                    {
                        return;
                    }
                    else if((start.day() < currentDate.day()) &&
                            (start.week() == currentDate.week()))
                    {
                        return;
                    }
                    
                    end.minute(0);
                    end.hour(start.hour() + 1);
                    end.day(start.day());
                    var newid = 0;
                    if(events.length > 0)
                    {
                        newid = events[events.length-1].id + 1;
                    }
                    currentEvent = {
                        id: newid,
                        title: 'Ny konsultation',
                        start: start,
                        end: end,
                        allDay: false,
                        editable: false};
                    $('#calendar').fullCalendar('refetchEvents');
                    displayEvents();
                    $('#calendar').fullCalendar('renderEvent', currentEvent);
                },
                timeFormat: 'H(:mm)' // uppercase H for 24-hour clock
            });
            insertEvent("Markus", "10/10/2017", "11:00", "12:00");
            displayEvents();
        });
        </script>
    </body>
</html>
