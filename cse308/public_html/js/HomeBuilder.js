var subject;
var semester;
var number;
var year;
var title;

function initHome() {
    var dataFile = "./js/SiteSaveTest.json";
    loadData(dataFile);
  

}

function loadData(jsonFile) {
    $.getJSON(jsonFile, function (json) {
	loadJData(json);
      
    });}


function loadJData(data) {
    // FIRST GET THE STARTING AND ENDING DATES
    subject =data.subject;
    number = data.number;
    document.getElementById("titleId").innerHTML = subject + number;
    document.getElementById("banner").innerHTML = subject + number+"-"+data.semester+data.year+"<br/>"
                                                   +data.title;
    

}



