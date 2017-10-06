<%-- 
    Document   : boka
    Created on : 2017-okt-06, 22:28:11
    Author     : markus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Boka tid</title>
        <meta name="description" content="" />
        <meta name="keywords" content="" />

        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>


        <link rel="stylesheet" href="css/style.css" />
        <link rel='stylesheet' href='fullcalendar/fullcalendar.css' />
        <script src='js/jquery.min.js'></script>
        <script src='js/moment.min.js'></script>
        <script src='fullcalendar/fullcalendar.js'></script>
        <script src='fullcalendar/locale/sv.js'></script>
        <link rel='stylesheet' href='jquery-ui/jquery-ui.css' />
        <script src="jquery-ui/jquery-ui.js"></script>
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
                // TODO: Gör så att dialog rutan försvinner.
                $('#dialog').dialog("close");
                currentEvent = (function () { return; });
            });

            $('#calendar').fullCalendar({
                    // put your options and callbacks here
                eventClick: function(calEvent, jsEvent, view) {
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
            })
            displayEvents();
        });
        </script>
    </body>
</html>
