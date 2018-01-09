
//MOTOR
int M1A = 8;
int M1B = 9;
int M2A = 10;
int M2B = 11;
int EN2 = 12;
int EN1 = 13;
int flag = 0;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(M1A,OUTPUT);
  pinMode(M1B,OUTPUT);
  pinMode(M2A,OUTPUT);
  pinMode(M2B,OUTPUT);
  pinMode(EN1,OUTPUT);
  pinMode(EN2,OUTPUT);

}

void loop() {
  // put your main code here, to run repeatedly:
  
  digitalWrite(EN1,HIGH);
  digitalWrite(EN2,HIGH);
  
  if(flag==0){
    forward();
    delay(2000);
    backward();
    delay(2000);
    leftForward();
    delay(1000);
    rightBackward();
    delay(1000);
    rightForward();
    delay(1000);
    leftBackward();
    delay(1000);
    left();
    delay(750);
    right();
    delay(750);
    flag = 1;
  }
  doNothing();

}

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
