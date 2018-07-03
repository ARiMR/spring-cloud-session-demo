document.addEventListener("DOMContentLoaded", function (event) {
    fetch('/api/principal', {
        method: 'GET',
        credentials: 'include'
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (principal) {
            try {
                console.log(principal);
                document.getElementById("user-info").innerHTML = JSON.stringify(principal, undefined, 2);
            } catch (e) {
                console.log("Not authenticated");
                document.getElementById("user-info").innerHTML = "Not authenticated";
            }
        })
        .catch(function (reason) {
            console.log(reason);
            document.getElementById("user-info").innerHTML = JSON.stringify(reason, undefined, 2);
        });
});