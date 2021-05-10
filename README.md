# FinalExamWeb

Test1 - SuiteTravelocityTest1.xml
Search for a flight from LAS to LAX, 1 adult (clicking on Flight/Roundtrip). Dates should be at least two month in the future and MUST​ be selected using the datepicker calendar.

Test2 - SuiteTravelocityTest2A.xml
Verify the result page using the following: a. There is a box that allow you to order by Price, Departure, Arrival and Duration.

Test2B -> not apply

Test2C - SuiteTravelocityTest2C.xml
Flight duration is present on every result
Test2D - SuiteTravelocityTest2D.xml
The flight detail and baggage fees is present on every result

Test3 - SuiteTravelocityTest3.xml
Sort by duration > shorter. Verify the list was correctly sorted. a. From this step select elements on the list using the button Select

Test4 - SuiteTravelocityTest4.xml
In the page (Select your departure to Los Angeles), select the first result.

Test5 - SuiteTravelocityTest5.xml
In the new page (Select your departure to Las Vegas), select the third result. 
a. if exists the pop-up asking for “flight with a Hotel”, select “no, thanks, maybe later” -> Not Apply

Test6 - SuiteTravelocityTest6.xml
Verify trip details in the new page, by: a. Trip total price is present b. Departure and return information is present c. Price guarantee text is present

Test7 & 8 - SuiteTravelocityTest7_8.xml
Press Continue Booking button. 8. Verify the “Who’s travelling” page is opened by choosing at least 5 validations to be performed.

Some notes:
1. The calendar is tested only in the first suite to gain speed for the rest of the tests.
2. The rest of the test will start at the flight search page directly.
