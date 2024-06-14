CREATE TABLE "User" (
                        "UserId" SERIAL PRIMARY KEY,
                        "Login" VARCHAR(255),
                        "Password" VARCHAR(255),
                        "Role" VARCHAR(50),
                        "Name" VARCHAR(255),
                        "Phone" VARCHAR(50),
                        "Email" VARCHAR(255)
);

CREATE TABLE "Service" (
                           "ServiceId" SERIAL PRIMARY KEY,
                           "Name" VARCHAR(255),
                           "Description" TEXT
);

CREATE TABLE "Doctor" (
                          "UserId" INTEGER PRIMARY KEY,
                          "ServiceId" INTEGER,
                          FOREIGN KEY ("UserId") REFERENCES "User"("UserId"),
                          FOREIGN KEY ("ServiceId") REFERENCES "Service"("ServiceId")
);

CREATE TABLE "Patient" (
                           "UserId" INTEGER PRIMARY KEY,
                           "Balance" INTEGER,
                           FOREIGN KEY ("UserId") REFERENCES "User"("UserId")
);

CREATE TABLE "Appointment" (
                               "AppointmentID" SERIAL PRIMARY KEY,
                               "doctor_id" INTEGER,
                               "patient_id" INTEGER,
                               "DateTime" TIMESTAMP,
                               "Status" VARCHAR(50),
                               FOREIGN KEY ("doctor_id") REFERENCES "Doctor"("UserId"),
                               FOREIGN KEY ("patient_id") REFERENCES "Patient"("UserId")
);

CREATE TABLE "Review" (
                          "ReviewID" SERIAL PRIMARY KEY,
                          "doctor_id" INTEGER,
                          "patient_id" INTEGER,
                          "Rating" DOUBLE PRECISION,
                          "Comment" TEXT,
                          FOREIGN KEY ("doctor_id") REFERENCES "Doctor"("UserId"),
                          FOREIGN KEY ("patient_id") REFERENCES "Patient"("UserId")
);
