import pymysql.cursors
import sys
from sdk.api.message import Message
from sdk.exceptions import CoolsmsException
 
connection = pymysql.connect(host='', user='', password='', db='')
     
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
    
    api_key = ""
    api_secret = ""
    str2 = "\"" + str1 +"\""
    #phone = ""
    params = dict()
    params['type'] = 'sms'
    params['to'] = str2
    params['from'] = ''
    params['text'] = ''
    
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