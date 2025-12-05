class Car:
    def __init__(self, manufacturer, model, make, transmission, color):
        self.manufacturer = manufacturer
        self.model = model
        self.make = make
        self.transmission = transmission
        self.color = color

    def accelerate(self):
        print(self.manufacturer + " " + self.model + " has started moving")

    def stop(self):
        print(self.manufacturer + " " + self.model + " has stopped moving")


car1 = Car("Honda", "Civic", "2020", "Automatic", "Blue")
car2 = Car("Ford", "Mustang", "2019", "Manual", "Yellow")
car3 = Car("BMW", "X5", "2021", "Automatic", "Silver")

car1.accelerate()
car1.stop()
