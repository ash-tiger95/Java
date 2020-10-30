import pymysql.cursors
import sys
from sdk.api.message import Message
from sdk.exceptions import CoolsmsException
 
connection = pymysql.connect(host='172.20.10.4', user='subin', password='1234', db='phone')
     
if __name__ == "__main__":
    
    try:
        with connection.cursor() as cursor:
            sql = 'SELECT * FROM phonedata'
            cursor.execute(sql)
            result = cursor.fetchall()
            
            rc=cursor.rowcount
            i=0
            str1=''
            
            while i < rc:
                print(result[i][1])
                if i == (rc-1):
                    str1 += "'"+result[i][1]+"'"
                else:
                    str1 += "'"+result[i][1]+"', "
                i+=1
        
            print(str1)
        
    finally:
        connection.close()
    
    api_key = "NCSYJ8OCL6ZIFUFQ"
    api_secret = "OAXHBOR9ZEXYAMAXY6PG798L8BNIR936"
    str2 = "\"" + str1 +"\""
    #phone = "01035950104"
    params = dict()
    params['type'] = 'sms'
    params['to'] = str2
    params['from'] = '01024601866'
    params['text'] = '172.20.10.4/PWDI/index.php collision!!'
    
    cool = Message(api_key, api_secret)
    
    
    try:
        response = cool.send(params)
        print("Success Count : %s" % response['success_count'])
        print("Error Count : %s" % response['error_count'])
        print("Group ID : %s" % response['group_id'])
        
        if "error_list" in response:
            print("Error List : %s" % response['error_list'])
    except CoolsmsException as e:
        print("Error code : %s" % e.code)
        print("Error Message : %s" % e.msg)
    
    sys.exit()