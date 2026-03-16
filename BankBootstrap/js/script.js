function navigate(element,page){

document.querySelectorAll(".sidebar a").forEach(link=>{
link.classList.remove("active");
});

element.classList.add("active");

loadPage(page);

}


function loadPage(page){

let content=document.getElementById("content");

if(page==="home"){

content.innerHTML=`

<h1 class="mb-4 fw-bold">Welcome to Our Bank</h1>

<p>
Our Bank is committed to providing secure, reliable and modern banking
services to individuals and businesses.
</p>

<p>
We combine traditional banking values with innovative digital technology
so customers can manage their finances easily and safely.
</p>

<div class="row mt-4">

<div class="col-md-4">
<div class="card shadow-sm">
<div class="card-body text-center">
<i class="bi bi-piggy-bank fs-1 text-warning"></i>
<h5 class="mt-3">Savings Accounts</h5>
<p>Secure savings at low interest rates.</p>
</div>
</div>
</div>

<div class="col-md-4">
<div class="card shadow-sm">
<div class="card-body text-center">
<i class="bi bi-cash-stack fs-1 text-warning"></i>
<h5 class="mt-3">Loans</h5>
<p>Flexible loan options for customers.</p>
</div>
</div>
</div>

<div class="col-md-4">
<div class="card shadow-sm">
<div class="card-body text-center">
<i class="bi bi-phone fs-1 text-warning"></i>
<h5 class="mt-3">Digital Banking</h5>
<p>Bank anytime with online services.</p>
</div>
</div>
</div>

</div>

`;

}

else if(page==="about"){

content.innerHTML=`

<h1 class="mb-4 fw-bold">About Our Bank</h1>

<p>
Our Bank was established with a vision of providing secure and
accessible financial services to customers across the country.
</p>

<p>
We aim to combine trust, innovation and customer satisfaction
to deliver modern banking solutions.
</p>

<p>
With digital banking, savings products and lending services,
we help customers achieve their financial goals.
</p>

`;

}

else if(page==="services"){

content.innerHTML=`

<h1 class="mb-4 fw-bold">Our Services</h1>

<div class="row">

<div class="col-md-4 mb-4">
<div class="card shadow text-center">
<div class="card-body">
<h5>Account Opening</h5>
<p>Open a secure savings account.</p>
<button class="btn btn-custom"
onclick="openServicePage('Account Opening')">
Apply
</button>
</div>
</div>
</div>

<div class="col-md-4 mb-4">
<div class="card shadow text-center">
<div class="card-body">
<h5>Loan Application</h5>
<p>Apply for personal loans.</p>
<button class="btn btn-custom"
onclick="openServicePage('Loan Application')">
Apply
</button>
</div>
</div>
</div>

<div class="col-md-4 mb-4">
<div class="card shadow text-center">
<div class="card-body">
<h5>Credit Card</h5>
<p>Get exciting card benefits.</p>
<button class="btn btn-custom"
onclick="openServicePage('Credit Card')">
Apply
</button>
</div>
</div>
</div>

</div>

`;

}

else if(page==="netbanking"){

content.innerHTML=`

<h1 class="mb-4 fw-bold">Netbanking</h1>

<div id="login-form" class="col-md-6">

<h3>Login</h3>

<form onsubmit="loginSubmit(event)" autocomplete="off">

<div class="mb-3">
<label>Email</label>
<input class="form-control" required>
</div>

<div class="mb-3">
<label>Password</label>
<input type="password" class="form-control" required>
</div>

<button class="btn btn-custom w-100">Login</button>

</form>

<p class="mt-3">
<span style="cursor:pointer;color:blue"
onclick="showSignup()">
New user? Sign up here
</span>
</p>

</div>


<div id="signup-form" class="col-md-6" style="display:none">

<h3>Sign Up</h3>

<form onsubmit="signupSubmit(event)" autocomplete="off">

<div class="mb-3">
<label>Full Name</label>
<input class="form-control" required>
</div>

<div class="mb-3">
<label>Email</label>
<input class="form-control" required>
</div>

<div class="mb-3">
<label>Password</label>
<input type="password" class="form-control" required>
</div>

<button class="btn btn-custom w-100">Sign Up</button>

</form>

<p class="mt-3">
<span style="cursor:pointer;color:blue"
onclick="showLogin()">
Already have an account? Login
</span>
</p>

</div>

`;

}

else if(page==="contact"){

content.innerHTML=`

<h1 class="mb-4 fw-bold">Contact Us</h1>

<form onsubmit="submitContact(event)" class="col-md-6">

<div class="mb-3">
<label>Name</label>
<input class="form-control" required>
</div>

<div class="mb-3">
<label>Email</label>
<input type="email" class="form-control" required>
</div>

<div class="mb-3">
<label>Message</label>
<textarea class="form-control"></textarea>
</div>

<button class="btn btn-custom">Submit</button>

</form>

`;

}

}


function openServicePage(service){

let content=document.getElementById("content");

content.innerHTML=`

<h1 class="mb-4 fw-bold">${service}</h1>

<p>Please fill the form below to apply.</p>

<form onsubmit="serviceSubmit(event)" class="col-md-6 mt-3">

<div class="mb-3">
<label>Full Name</label>
<input class="form-control" required>
</div>

<div class="mb-3">
<label>Email</label>
<input class="form-control" required>
</div>

<div class="mb-3">
<label>Phone Number</label>
<input class="form-control" required>
</div>

<button class="btn btn-custom w-100">
Submit Request
</button>

</form>

<br>

<button class="btn btn-secondary mt-3"
onclick="loadPage('services')">
← Back to Services
</button>

`;

}


function serviceSubmit(e){
e.preventDefault();
alert("Service request submitted successfully!");
}


function loginSubmit(e){
e.preventDefault();
alert("Login Successful");
e.target.reset();
}


function signupSubmit(e){
e.preventDefault();
alert("Signup Successful!");
e.target.reset();
showLogin();
}


function showSignup(){

document.getElementById("login-form").style.display="none";
document.getElementById("signup-form").style.display="block";

}


function showLogin(){

document.getElementById("login-form").style.display="block";
document.getElementById("signup-form").style.display="none";

}


function submitContact(e){

e.preventDefault();

document.getElementById("content").innerHTML=`

<h2>Thank You!</h2>
<p>We received your request and will contact you soon.</p>

`;

}


window.onload=function(){
loadPage("home");
};