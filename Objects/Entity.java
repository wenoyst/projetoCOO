package Objects;



public interface Entity{

double getY();

double getX();

double getDy();

double getDx();

void setDy(double dy);

void setDx(double dx);

void setX(double x);

void setY(double y);

void setRadius(double radius);

double getRadius();

double getExplosionEnd();

double getExplosionStart();

void setExplosionEnd(double EE);

void setExplosionStart(double ES);

void setState(int state);

int getState();

}

