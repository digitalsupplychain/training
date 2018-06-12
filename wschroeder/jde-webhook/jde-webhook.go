package main

import (
	"bytes"
	"fmt"
	"net/http"
)

func main() {
	url := "https://srvjde7.grupoassa.com:8103/DV920/SalesOrderManager"

	var Str = []byte(`{"title":"Buy cheese and bread for breakfast."}`)
	resp, err := http.Post(url, "text/xml", bytes.NewBuffer(Str))

	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()

	fmt.Println("response Status:", resp.Status)
}