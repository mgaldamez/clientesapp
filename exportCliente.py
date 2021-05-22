import os
import pymysql as mysql
import csv
from datetime import date


fecha = date.today()
CURR_DIR = os.path.dirname(os.path.realpath(__file__))
fila_name = CURR_DIR+"\Clientes_"+str(fecha)+".csv"
db = mysql.connect(host='localhost',user='root',password='root',database='db_clientesapp')


with db:
    with db.cursor() as cursor:
        cursor.execute("select * from clientes")
        clientes = cursor.fetchall()
        header = [i[0] for i in cursor.description]
        fp = open(fila_name,"w", newline='')
        export = csv.writer(fp)
        export.writerow(header)
        export.writerows(clientes)
        fp.close()

