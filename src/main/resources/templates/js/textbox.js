//document.getElementById('name');
document.get
    document.getElementById('nameForm').onsubmit = function(event) {
        event.preventDefault(); // Prevent the default form submission
        const name = document.getElementById('name').value;
        window.location.href = '/greeting?name=' + encodeURIComponent(name);
    };


function addName () {
    alert('You clicked the button!');
    document.getElementById('greetingLink').value = "/greeting?=" + document.getElementByID('name').value;
    return "/greeting?=" + document.getElementByID('name').innerText;
//    var cost = 25;
//    var quantity = document.getElementById('quantity').value;
//    var totalcost = (cost * quantity);
//
//    document.getElementById('total').innerText = totalcost;
}


function buttonClicked() {
  alert('You clicked the button!');
  document.getElementById("h1").innerHTML = "New text!";
}