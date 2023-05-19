# Automatic-System-for-Traffic-Control-and-Management
This project is made up of a System which controls vehicular traffic over an intersection connecting 4 roads. The traffic flow data is fetched real-time through Tom Tom's Traffic API. The JSON File is parsed using GSON library. Different parameters to define traffic flow include Current Average Speed, FreeFlow Average Speed, Current Travel Time, FreeFlow Travel Time on the road segment and the Confidence Level of the real-time data. This data is used for calculation of the green signal time for each road segment and this process is dynamic.
Further, when the green signal time is calculated, this data is sent over to the arduino board using Serial Communication.

Resources Location :
Time Controller Code : src-> main -> java/com/example -> timeController.java
Code to Fetch traffic flow data : src-> main -> java/com/example -> data.java


