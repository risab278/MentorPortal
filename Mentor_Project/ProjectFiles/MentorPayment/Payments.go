package main

import (
	"fmt"
	"net/http"
	"github.com/gorilla/mux"
	"encoding/json"
	"io/ioutil"
	"context"
	"log"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo/options"
)

type Payment struct {
	Id       string `json:"Id"`
	Mid      string `json:"MId"`
	Tid      string `json:"Tid"`
	Amt      string `json:"Amt"`
	Datetime string `json:"Datetime"`
	Remarks  string `json:"Remarks"`
}

var collection *mongo.Collection

func init(){
	clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
	client, err := mongo.Connect(context.TODO(), clientOptions)

	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Connected to MongoDB!")
	collection = client.Database("pay").Collection("payments")

}



func createPayment(w http.ResponseWriter, req *http.Request){
	reqbody,_:=ioutil.ReadAll(req.Body)
	var payment Payment
	json.Unmarshal(reqbody,&payment)


	insertResult, _ := collection.InsertOne(context.TODO(), payment)

	fmt.Println("Inserted a Single Document: ", insertResult.InsertedID)
	fmt.Println("Payment received")

}

func getPayment(w http.ResponseWriter, req *http.Request)  {
	vars := mux.Vars(req)
	idval := vars["id"]

	filter := bson.M{"id":idval}
	var result Payment

	err := collection.FindOne(context.TODO(), filter).Decode(&result)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Printf("Found a single document: %+v\n", result)


	json.NewEncoder(w).Encode(result)


}

func getAllPayment(w http.ResponseWriter, req *http.Request)  {

	findOptions := options.Find()
	var results []Payment

	cur, err:= collection.Find(context.TODO(), bson.D{}, findOptions)
	if err != nil {
		log.Fatal(err)
	}

	for cur.Next(context.TODO()){
		var elem Payment
		err :=  cur.Decode(&elem)
		if err != nil {
			log.Fatal(err)
		}

		results = append(results, elem)

	}

	if err := cur.Err(); err != nil {
		log.Fatal(err)
	}
	cur.Close(context.TODO())

	fmt.Printf("All Payements are: %+v\n", results)

	json.NewEncoder(w).Encode(results)

}

func updatePayment(w http.ResponseWriter, req *http.Request){
	vars := mux.Vars(req)
	idval := vars["id"]

	reqbody,_:=ioutil.ReadAll(req.Body)
	var pp Payment
	json.Unmarshal(reqbody, &pp)

	findOptions :=  bson.D{
		{"$set", bson.D{{"Amt", pp.Amt}}},
	}

	filter := bson.M{"id":idval}

	_,err := collection.UpdateOne(context.TODO(), filter, findOptions)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Payment is Updated")

}

func deletePayment(w http.ResponseWriter, req *http.Request){

	vars := mux.Vars(req)
	idval := vars["id"]

     filter1:=bson.M{"id":idval}

   deleteResult, err := collection.DeleteMany(context.TODO(),filter1)
  if err != nil {
      log.Fatal(err)
  }
  fmt.Printf("Deleted %v documents in the person collection\n", deleteResult.DeletedCount)

  }




func main() {
	myRouter := mux.NewRouter().StrictSlash(true)


	myRouter.HandleFunc("/createpayment", createPayment).Methods("POST")
	myRouter.HandleFunc("/getpayment/{id}", getPayment)
	myRouter.HandleFunc("/getallpayments", getAllPayment)
	myRouter.HandleFunc("/updatepayment/{id}", updatePayment).Methods("PUT")
	myRouter.HandleFunc("/deletepayment/{id}", deletePayment).Methods("DELETE")

	http.ListenAndServe(":9896", myRouter)
}

