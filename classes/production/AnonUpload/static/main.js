
//getFiles takes filedata and turns it into HTML.

function getFiles(filesData) {
    //for each file in the file data:
    for (var i in filesData) {

        //make a new anchor tag
        var elem = $("<a>");

        //set the anchor tag's href to files/ + this file's filename.
        elem.attr("href", "files/" + filesData[i].filename);

        //set the text of the anchor to the original filename. (ex. picture.png)
        elem.text(filesData[i].originalFilename);

         // make a new <br> element and append it to the page
         // <br> elements are used mostly to force the browser
         // to render a new line character
        $("#fileList").append(elem);
        var elem2 = $("<br>");
        $("#fileList").append(elem2);
    }
}


//this issues a GET request to /files , then it completes and runes the "getFiles" method
$.get("/files", getFiles);