
//DTMF Pins
int DTMF1 = A5;
int DTMF2 = A4;
int DTMF3 = A3;
int DTMF4 = A2;
int StD = A1;
//int VCCDTMF = 7;

//MOTOR Pins
int M1A = 8;
int M1B = 9;
int M2A = 10;
int M2B = 11;
int EN1 = 12;
int EN2 = 13;

boolean pwdFlag = false;
int prevInput = -1;
String password = "1234";
String inputPwd = "";
int input = -1;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(DTMF1,INPUT);
  pinMode(DTMF2,INPUT);
  pinMode(DTMF3,INPUT);
  pinMode(DTMF4,INPUT);
  pinMode(M1A,OUTPUT);
  pinMode(M1B,OUTPUT);
  pinMode(M2A,OUTPUT);
  pinMode(M2B,OUTPUT);
  //pinMode(VCCDTMF,OUTPUT);
  pinMode(EN1,OUTPUT);
  pinMode(EN2,OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  
  //digitalWrite(VCCDTMF,HIGH);
  digitalWrite(EN1,HIGH);
  digitalWrite(EN2,HIGH);
  
  //Loop for password
  while(!pwdFlag){
    if(myRead(StD)==1){
      input = binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1));
    }
    if(input == 10){
      Serial.println("Reset");
      prevInput = -1;
      input = -1;
      inputPwd = "";
      continue;
    }
    if(input == prevInput){
      //delay(100);
      continue;
    }
    prevInput = input;
    inputPwd += String(input);
    Serial.println(inputPwd);
    
    if(inputPwd == password){
      pwdFlag = true;
      Serial.println("Password Correct");
    }
  }
  
 /*if(digitalRead(StD)){
  int p1 = digitalRead(DTMF1);
  int p2 = digitalRead(DTMF2);
  int p3 = digitalRead(DTMF3);
  int p4 = digitalRead(DTMF4);
  
  Serial.println("haha");
  
  int inp = p1*1 + p2*10 + p3*100 + p4*1000;
  String output = String(inp,DEC);
  
  Serial.println(output);
  Serial.println(inp);
 }
 else{
   Serial.println("Press any key.");
 }
 
 delay(1000);*/
 
  // If no key is pressed then do nothing
  /*while(myRead(StD)==0){
    doNothing();
    Serial.println("doNothing");
  }*/
  
  // Move forward if 2 is pressed.
  if(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==2){
    forward();
    Serial.println("forward");
  }
  
  // Move backward if 8 is pressed.
  if(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==8){
    backward();
    Serial.println("backward");    
  }
  
  // Move LeftForward if key 1 is pressed.
  if(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==1){
    leftForward();
    Serial.println("leftForward");
  }
  
  // Move RightForward if key 3 is pressed.
  if(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==3){
    rightForward();
    Serial.println("rightForward");
  }
  
  // Move LeftBackward if key 7 is pressed.
  while(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==7){
    leftBackward();
    Serial.println("leftBackward");
  }
  
  // Move RightBackward if key 9 is pressed.
  if(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==9){
    rightBackward();
    Serial.println("rightBackward");
  }
  
  // Turn Left if key 4 is pressed.
  if(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==4){
    left();
    Serial.println("left");
  }
  
  // Turn Right if key 6 is pressed.
  if(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==6){
    right();
    Serial.println("right");
  }
  
  if(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==5){
    doNothing();
    Serial.println("doNothing");
  }
  
  // To lock the car.
  if(myRead(StD)==1 && binToDec(myRead(DTMF4),myRead(DTMF3),myRead(DTMF2),myRead(DTMF1))==10){
    pwdFlag = false;
    Serial.println("Locked. Enter password again to operate.");
  }
 
  /*Serial.println(A2);
  int b = myRead(A1);
  if(b==1){
  int b1 = myRead(A2);
  int b2 = myRead(A3);
  int b3 = myRead(A4);
  int b4 = myRead(A5);
  int command = b1*8 + b2*4 + b3*2 + b4;
  Serial.println(command);
  }*/
 
}

// Analog read of mentioned pin and give respective value of 1 or 0 according to voltage threshold.
int myRead(int s){
  int value = analogRead(s) >= 150 ? 1 : 0;
  return value;
}

// Converting BCD to decimal value.
int binToDec(int b4, int b3, int b2, int b1){
  return (b4*8 + b3*4 + b2*2 + b1*1);
}  

// Driving the motor for car movement.

void doNothing(){
    digitalWrite(M1A,LOW);
    digitalWrite(M1B,LOW);
    digitalWrite(M2A,LOW);
    digitalWrite(M2B,LOW);
}

void forward(){
    digitalWrite(M1A,HIGH);
    digitalWrite(M1B,LOW);
    digitalWrite(M2A,HIGH);
    digitalWrite(M2B,LOW);
}

void backward(){
    digitalWrite(M1A,LOW);
    digitalWrite(M1B,HIGH);
    digitalWrite(M2A,LOW);
    digitalWrite(M2B,HIGH);
}

void leftForward(){
    digitalWrite(M1A,LOW);
    digitalWrite(M1B,LOW);
    digitalWrite(M2A,HIGH);
    digitalWrite(M2B,LOW);
}

void rightForward(){
    digitalWrite(M1A,HIGH);
    digitalWrite(M1B,LOW);
    digitalWrite(M2A,LOW);
    digitalWrite(M2B,LOW);
}

void leftBackward(){
    digitalWrite(M1A,LOW);
    digitalWrite(M1B,LOW);
    digitalWrite(M2A,LOW);
    digitalWrite(M2B,HIGH);
}

void rightBackward(){
    digitalWrite(M1A,LOW);
    digitalWrite(M1B,HIGH);
    digitalWrite(M2A,LOW);
    digitalWrite(M2B,LOW);
}

void left(){
    digitalWrite(M1A,LOW);
    digitalWrite(M1B,HIGH);
    digitalWrite(M2A,HIGH);
    digitalWrite(M2B,LOW);
}

void right(){
    digitalWrite(M1A,HIGH);
    digitalWrite(M1B,LOW);
    digitalWrite(M2A,LOW);
    digitalWrite(M2B,HIGH);
}

