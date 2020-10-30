#include <Wire.h>
#include <Adafruit_MLX90614.h>
#define CURRENT 20
float vout=0.0;
float vin =0.0;
float R1 = 30000.0;
float R2 = 7500.0;
int value=0;
int total_val = 0;
 
Adafruit_MLX90614 mlx = Adafruit_MLX90614();
 
void setup() {
  Serial.begin(9600);
  pinMode(A0,INPUT);
  
  mlx.begin();  
  Serial.println("vibration volt temperature Ambient temp_differ");
}

void loop() {
  //진동센서
  int val1;
  val1 = analogRead(A2);
  Serial.print(val1,DEC);
  Serial.print(",");
  
  //전압센서
  value = analogRead(A0);
  vout = (value*5.0)/1024.0;
  vin = vout/(R2/(R1+R2));
  //Serial.print("V:");
  Serial.print(vin);
  Serial.print(",");

  //물체온도
  Serial.print(mlx.readObjectTempC());
  Serial.print(",");
  //주변온도
  Serial.print(mlx.readAmbientTempC()); 
  Serial.print(",");
  //물체온도-주변온도
  Serial.print(mlx.readObjectTempC()-mlx.readAmbientTempC());
  Serial.println();
  
 
  delay(1000);

}
