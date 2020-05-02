import mysql.connector   
import random  
cnx = mysql.connector.connect(user='root', password='PrashaM',
                              host='127.0.0.1',
                              database='Service_NEST')

email = "bhaveshk2000@gmail.com"
Name = ["Huzaifa","Mayank","Navam","Peeyush","Gaurav","Aditya","Tushar","Sudhir","Ayush","Garvit","Jaskirat","Rohit","Ramesh","Akansha","Saianshi","Aman","Aditya","Harsh","Nikhil","Nishant","Chandan","Samarth","Abhinay","Diptanshu","Ritvik","Gavish","Shrey","Vishesh","Anmol","Sanskar","Sandeep","Swastik","Kinshuk","Saka","Abhishek","Abhimanyu","Ramyaa","Saranshi","Anshika","Soumyaa","Vanshika"]
curr_id = 0
SirNames = ["Khan","Joshi","Sundriyal","Jain","Khurana","Sharma","Kumar","Verma","Bhardwaj","Chadha","Singh","Arora","Pawar","Jagga","Katyal"]
SirNames2 = ["Khatri","Aggarwal","Jaiswal","Pandey","Narayan","Gupta","Goyal","Talwar","Negi","Padukone","Kappor","Bhatnagar","Baba","Kohli","Pant"]
Loc = ["North","East","South","West","Central"]
sub = ["Maths","English","Science","Social Science","Economics","Hindi","Computer Science"]
books = ["NCERT Maths class 11","NCERT Maths class 12","NCERT Biology class 12","NCERT Biology class 11","NCERT Physics class 12","NCERT Physics class 11","NCERT Chemistry class 11","NCERT Chemistry class 12","NCERT English class 11","NCERT English class 12","NCERT Hindi class 11","NCERT Hindi class 12"]
book_count = len(books)
try:
	cursor = cnx.cursor()
	cursor = cnx.cursor(buffered=True)
	
	for i in Loc:
		cursor.execute("insert Location values(%s)",(i,))

	for i in sub:
		cursor.execute("insert Subjects values(%s)",(i,))

	for i in books:
		cursor.execute("insert Books values(%s)",(i,))
	##Doctors
	for i in range(15):
		for j in range(15):	
			curr_id = curr_id+1
			Nam = Name[i]+" "+SirNames[j]
			Age = random.randrange(18,40,1)
			Gender = random.choice(['M','F','O'])
			Home_Address = "Delhi"
			Clinic_Address = "Delhi"
			Phone_Number = random.randrange(1000000000,9999999999,1)
			Experience = random.randrange(0,30,1)
			Availability = random.randrange(0,2,1)
			Fees_Per_Visit = random.randrange(300,500,50)
			
			Speciality = random.choice(["Eyes","ENT","Cardiologist","Physician","Dermatologist","Plastic Surgery"])
			Ratings = random.randrange(0,50,5)
			Ratings = Ratings/5
			Service_Category = 1
			Location = random.randrange(0,4,1)
			cursor.execute("insert Service_Provider values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",(curr_id,Nam,Age,Gender,Home_Address,Phone_Number,Ratings,Service_Category,1,email,Availability))
			cursor.execute("insert Doctors values(%s,%s,%s,%s,%s)",(curr_id,Clinic_Address,Experience,Fees_Per_Visit,Speciality))
			cursor.execute("insert Works_in values(%s,%s)",(curr_id,Loc[Location]))

	cursor.execute("select * from Doctors")
	result = cursor.fetchall()

	#Location

	
	#//Nurses

	for i in range(5,25):
		for j in range(15):
			curr_id = curr_id+1
			Nam = Name[i]+" "+SirNames[j]
			Age = random.randrange(18,40,1)
			Gender = random.choice(['M','F','O'])
			Home_Address = "Delhi"
			Phone_Number = random.randrange(1000000000,9999999999,1)
			Experience = random.randrange(0,30,1)
			Availability = random.randrange(0,2,1)
			Fees_Per_12 = random.randrange(300,500,50)
			Fees_Per_24 = random.choice(["eyes","bed rest","heart"])
			
			Ratings = random.randrange(0,50,5)
			Ratings = Ratings/5
			Service_Category = 2
			Location = random.randrange(0,4,1)
			cursor.execute("insert Service_Provider values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",(curr_id,Nam,Age,Gender,Home_Address,Phone_Number,Ratings,Service_Category,1,email,Availability))
			cursor.execute("insert Nurses values(%s,%s,%s,%s,%s)",(curr_id,Home_Address,Experience,Fees_Per_12,Fees_Per_24))
			cursor.execute("insert Works_in values(%s,%s)",(curr_id,Loc[Location]))

	cursor.execute("select * from Nurses")
	result = cursor.fetchall()
	
	

	

	#//Plumbers
	for i in range(10,30):
		for j in range(15):
			curr_id = curr_id+1
			Nam = Name[i]+" "+SirNames[j]
			Age = random.randrange(18,40,1)
			Gender = random.choice(['M','F','O'])
			Home_Address = "Delhi"
			Phone_Number = random.randrange(1000000000,9999999999,1)
			Experience = random.randrange(0,30,1)
			Availability = random.randrange(0,2,1)
			Fees_Per_Visit = random.randrange(300,500,50)
			Fees_As_work = 0
			
			Ratings = random.randrange(0,50,5)
			Ratings = Ratings/5
			Service_Category = 3
			Location = random.randrange(0,4,1)
			cursor.execute("insert Service_Provider values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",(curr_id,Nam,Age,Gender,Home_Address,Phone_Number,Ratings,Service_Category,1,email,Availability))
			cursor.execute("insert Plumbers values(%s,%s,%s,%s)",(curr_id,Fees_Per_Visit,Fees_As_work,Experience))
			cursor.execute("insert Works_in values(%s,%s)",(curr_id,Loc[Location]))
	
	
	#//Electrician
	for i in range(30):
		for j in range(15):
			curr_id = curr_id+1
			Nam = Name[i]+" "+SirNames2[j]
			Age = random.randrange(18,40,1)
			Gender = random.choice(['M','F','O'])
			Home_Address = "Delhi"
			Phone_Number = random.randrange(1000000000,9999999999,1)
			Experience = random.randrange(0,30,1)
			Availability = random.randrange(0,2,1)
			Fees_Per_Visit = random.randrange(300,500,50)
			Fees_As_work = 0
			
			Ratings = random.randrange(0,50,5)
			Ratings = Ratings/5
			Service_Category = 4
			Location = random.randrange(0,4,1)
			cursor.execute("insert Service_Provider values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",(curr_id,Nam,Age,Gender,Home_Address,Phone_Number,Ratings,Service_Category,1,email,Availability))
			cursor.execute("insert Electrician values(%s,%s,%s,%s)",(curr_id,Fees_Per_Visit,Fees_As_work,Experience))
			cursor.execute("insert Works_in values(%s,%s)",(curr_id,Loc[Location]))
	#//HouseKeeping
	for i in range(5,35):
		for j in range(15):
			curr_id = curr_id +1
			Nam = Name[i]+" "+SirNames2[j]
			Age = random.randrange(18,40,1)
			Gender = random.choice(['M','F','O'])
			Home_Address = "Delhi"
			Phone_Number = random.randrange(1000000000,9999999999,1)
			Experience = random.randrange(0,30,1)
			Availability = random.randrange(0,2,1)
			Fees_Per_Visit = random.randrange(300,500,50)
			Fees_As_work = 0
			
			Ratings = random.randrange(0,50,5)
			Ratings = Ratings/5
			Service_Category = 5
			Location = random.randrange(0,4,1)
			cursor.execute("insert Service_Provider values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",(curr_id,Nam,Age,Gender,Home_Address,Phone_Number,Ratings,Service_Category,1,email,Availability))
			cursor.execute("insert HouseKeeping values(%s,%s,%s,%s)",(curr_id,Fees_Per_Visit,Fees_As_work,Experience))
			cursor.execute("insert Works_in values(%s,%s)",(curr_id,Loc[Location]))
	# cursor.execute("select* from Plumbers")
	# cursor.execute("select* from Electrician")
	# cursor.execute("select* from HouseKeeping")

	#Tutors
	for i in range(5,35):
		for j in range(15):
			curr_id = curr_id +1
			Nam = Name[i]+" "+SirNames2[j]
			Age = random.randrange(18,40,1)
			Gender = random.choice(['M','F','O'])
			Home_Address = "Delhi"
			Phone_Number = random.randrange(1000000000,9999999999,1)
			Experience = random.randrange(0,30,1)
			Fees_Per_month = random.randrange(1000,3000,50)
			Fees_Per_week = random.randrange(300,1000,50)
			qualification = random.choice(["Btech","Mtech","BA","MA","BCom","CA","MS","BSc","MSc","MCom"])
			Availability = random.randrange(0,2,1)
			Ratings = random.randrange(0,50,5)
			Ratings = Ratings/5
			Service_Category = 6
			Location = random.randrange(0,4,1)
			cursor.execute("insert Service_Provider values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",(curr_id,Nam,Age,Gender,Home_Address,Phone_Number,Ratings,Service_Category,1,email,Availability))
			cursor.execute("insert Tutors values(%s,%s,%s,%s,%s)",(curr_id,Fees_Per_month,Fees_Per_week,Experience,qualification))
			cursor.execute("insert Works_in values(%s,%s)",(curr_id,Loc[Location]))
			tot_sub = random.randrange(0,7,1)
			curr_sub = random.sample(sub,tot_sub)
			for t in curr_sub:
				cursor.execute("insert Teaches values(%s,%s)",(curr_id,t))

	tim_m1 = ["9:00","9:30","8:30"]
	tim_m2 = ["20:30","21:00","21:30"]


	#Bookstores
	for i in range(5,35):
		for j in range(15):
			curr_id = curr_id +1
			Nam = Name[i]+" "+SirNames2[j]
			Age = random.randrange(18,40,1)
			Gender = random.choice(['M','F','O'])
			Home_Address = "Delhi"
			Phone_Number = random.randrange(1000000000,9999999999,1)
			Experience = random.randrange(0,30,1)
			Fees_Per_month = random.randrange(1000,3000,50)
			Fees_Per_week = random.randrange(300,1000,50)
			subjects = random.choice(["Maths","English","Science","Social Science","Economics","Hindi","Computer Science"])
			qualification = random.choice(["Btech","Mtech","BA","MA","BCom","CA","MS","BSc","MSc","MCom"])
			Availability = random.randrange(0,2,1)
			Location = random.choice(["North","East","South","West","Central"])
			Ratings = random.randrange(0,50,5)
			Ratings = Ratings/5
			second_hand = random.choice(["Yes","No"])
			stationary = random.choice(["Yes","No"])
			donations = random.choice(["Yes","No"])
			Timing = random.choice(tim_m1) +"-" + random.choice(tim_m2)
			Service_Category = 7
			cursor.execute("insert Service_Provider values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",(curr_id,Nam,Age,Gender,Home_Address,Phone_Number,Ratings,Service_Category,1,email,Availability))
			cursor.execute("insert Bookstores values(%s,%s,%s,%s,%s,%s)",(curr_id,Location,second_hand,stationary,donations,Timing))
			tot_books = random.randrange(0,book_count,1)
			curr_books = random.sample(books,tot_books)
			for t in curr_books:
				cursor.execute("insert Keeps values(%s,%s)",(curr_id,t))


#customers
	for i in range(0,30):
		for j in range(15):
			curr_id = curr_id +1
			Nam = Name[i]+" "+SirNames2[j]
			Age = random.randrange(18,40,1)
			Gender = random.choice(['M','F','O'])
			Home_Address = "Delhi"
			Phone_Number = random.randrange(1000000000,9999999999,1)
			Experience = random.randrange(0,30,1)
			Fees_Per_month = random.randrange(1000,3000,50)
			Fees_Per_week = random.randrange(300,1000,50)
			subjects = random.choice(["Maths","English","Science","Social Science","Economics","Hindi","Computer Science"])
			qualification = random.choice(["Btech","Mtech","BA","MA","BCom","CA","MS","BSc","MSc","MCom"])
			Availability = random.randrange(0,2,1)
			Location = random.choice(["North","East","South","West","Central"])
			Ratings = random.randrange(0,50,5)
			Ratings = Ratings/5
			Service_Category = 9
			cursor.execute("insert Customers values(%s,%s,%s,%s,%s,%s,%s,%s)",(curr_id,Nam,Age,Gender,Phone_Number,Home_Address,Location,email))
	

	#//Tests
	Test_Names = ['Blood','Urine','Xray','Ultrasound','Bone Density','Full Body','Eyes','liver function test','endoscopy','laparoscopy','kidney function test','pregnancy test']
	r=201
	for i in Test_Names:
		Test_Id = curr_id
		curr_id = curr_id+1
		Test_Name = i
		Cost = random.randrange(300,700,50)
		Require = ''
		Expected_Report = random.choice(["Today Evening","Tommorrow Evening","Tommorrow Evening"]) 
		Room = r
		r+=1
		#count+=1
		cursor.execute("insert Tests values(%s,%s,%s,%s,%s)",(Test_Name,Cost,Require,Expected_Report,Room))
	
	labNames = ["RanBaxy","Lal PathLabs","MahaRaja Labs","Max Labs","Fortis Labs","24*7 Labs"]
	Locations = ["North","South","East","West","Central"]
	tim_m1 = ["9:00","9:30","8:30"]
	tim_m2 = ["12:00","13:00","12:30"]
	tim_e1 = ["18:00","18:30","17:30"]
	tim_e2 = ["20:00","20:30","19:30"]
	
	#//Labs
	for i in range(6):
		for j in range(5):
			curr_id = curr_id + 1
			Nam = labNames[i]
			Location = Locations[j]
			Address = "Delhi"
			Phone_Number = random.randrange(1000000000,9999999999,1)
			Timing_Morning = random.choice(tim_m1) +"-" + random.choice(tim_m2)
			Timing_Evening = random.choice(tim_e1) +"-" + random.choice(tim_e2)
			Availability = random.randrange(0,2,1)
			Age = random.randrange(18,40,1)
			Gender = random.choice(['M','F','O'])
			Owner = Name[i]+SirNames[j]
			Home_Address = "Delhi"
			Mob_No =  random.randrange(1000000000,9999999999,1)
			Ratings = random.randrange(0,50,5)
			Ratings = Ratings/5
			Service_Category = 8
			cursor.execute("insert Service_Provider values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",(curr_id,Owner,Age,Gender,Home_Address,Phone_Number,Ratings,Service_Category,1,email,Availability))
			cursor.execute("insert Laboratories values(%s,%s,%s,%s)",(curr_id,Nam,Timing_Morning,Timing_Evening))
			tot_test = random.randrange(0,12,1)
			curr_test = random.sample(Test_Names,tot_test)
			for t in curr_test:
				cursor.execute("insert Takes values(%s,%s)",(curr_id,t))
	
	cursor.execute("select * from Laboratories")
	result = cursor.fetchall()
	for i in result:
		print(i)
	
finally:
	cnx.commit()
	cnx.close()


# ##### Queries

# def choose_role():
# 	print("Choose your role")
# 	print("1.Customer")
# 	print("2.Professional")
# 	print("3.Exit")
# 	return input()

# def choose_category():
# 	print("1.Health")
# 	print("2.Education")
# 	print("3.Day-to-Day")
# 	print("4.Back")
# 	return input()
# def choose_health():
# 	print("1.Doctor")
# 	print("2.Nurse")
# 	print("3.Laboratory")
# 	print("4.Back")
# 	return input()
# def choose_education():
# 	print("1.Private Tutor")
# 	print("2.BookStore")
# 	print("3.Back")
# 	return input()
# def choose_day-to-day():
# 	print("1.Plumber")
# 	print("2.Electrician")
# 	print("3.HouseKeeping")
# 	print("4.Back")
# 	return input()

# query_input = True

# while query_input:
	
# 	role = choose_role()
# 	if(role==1):
# 		choose_category()
# 	elif(role==2):
# 		choose_category()
# 	elif(role==3):
# 		query_input=False
# 	else:
# 		print
