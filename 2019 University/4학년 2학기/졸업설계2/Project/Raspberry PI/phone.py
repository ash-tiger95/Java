import pymysql
import serial
import subprocess
a = 1

#read serial data ion arduino
ser = serial.Serial('/dev/ttyACM0', 115200)  # Start serial communication

    
while (ser.readline() !=' ') and (a==1)  :
    data = ser.readline()  # Wait for line from Arduino and read it
    #print(data.split())  # Print the line to the console
    str1=' '
    str2=' '
    str1 = str(data.split()[0])
    str2 = str(data.split()[1])
    
    newstr1 = str1.replace(".","")
    newstr2 = str2.replace(".","")
    
    realnewstr1 = newstr1[:2] + '.' + newstr1[2:]
    realnewstr2 = newstr2[:3] + '.' + newstr2[3:]
    
    #connect db
    connection = pymysql.connect(host='', user='', password='', db='')

    #save db
    try:
        with connection.cursor() as cursor:
            sql = "INSERT INTO `gpsdata` (`Latitude`, `Longitude`) VALUES (%s, %s)"
            cursor.execute(sql, (realnewstr1, realnewstr2))

        connection.commit()
        
        filename='final.py'

        stdout = subprocess.check_output(["python", filename])
        
        a = 0

    finally:
        connection.close()
    


