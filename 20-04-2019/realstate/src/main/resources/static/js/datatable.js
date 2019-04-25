$(document).ready( function () {
    var table = $('#buildingTable').DataTable({
        "sAjaxSource": "/buildings",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "buildingName" },
            { "mData": "bedRooms" },
            { "mData": "bathRooms" },
            { "mData": "flatSize" },
            { "mData": "price" },
            { "mData": "streetAddress" },
            { "mData": "builtInDate" },
            { "mData": "registrationDate" },
            { "mData": "otherFeatures" },
            { "mData": "otherDetailing" },
            { "mData": "availableFor" },
            { "mData": "cities" },
            { "mData": "areas" },
            { "mData": "housings" },
            { "mData": "catagories" },
            { "mData": "constructionType" },
        ]
    })
});