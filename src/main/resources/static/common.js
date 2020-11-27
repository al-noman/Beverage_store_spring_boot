
$(document).on("click", ".menu-section .row", function (e){
    $(".container").removeAttr("align");
    $(".dynamic-content").remove();
    $(".homepage").hide();
    let $dataContent = $(".data-content").clone();
    $dataContent.addClass("dynamic-content");

    let $dataItem = $(this).find(".btn").attr("data-menu");
    switch ($dataItem) {
       case "incentiveMenu":
           let columns = ["#", "ID", "NAME", "TYPE", "ACTION"];
           createTableDefinition("Incentive Management", "Create new incentive",
               $dataContent, columns);
           getDataFromBackend("incentives", populateIncentiveTable);
           break;
       default:
           alert("something else");
    }
});

function createTableDefinition($contentLabel, $createButtonText, $dataContent, $columns) {
    $(".container").append($dataContent);
    $dataContent.find(".content-label").text($contentLabel);
    $dataContent.find(".create-button").text($createButtonText);
    $dataContent.show();

    let $columnDef = $dataContent.find("thead tr th").clone();
    $dataContent.find("thead tr").empty();
    let $columnHolder = $dataContent.find("thead tr");

    $columns.forEach($column => {
        let $columnItem = $columnDef.clone();
        $columnItem.text($column);
        $columnHolder.append($columnItem);
    });
    $(".container").append($dataContent)
}

function populateIncentiveTable($data){
    if ($data.status == "success"){
        console.log($data);
        let $columnDef = $(".dynamic-content").find("table tbody tr td").clone();
        let $rowDef = $(".dynamic-content").find("table tbody tr").clone();
        let $tableDef = $(".dynamic-content").find("table tbody");
        $tableDef.empty();

        let $props = [];
        if ($data.resource == "incentives"){
            $props = ["id", "name", "incentive_type"];
        }
        else if ($data.resource == "beverages"){
            $props = ["id", "name", "manufacturer", "quantity", "price", "incentiveId", "incentiveName", "incentiveType"];
        }
        for (let i=0; i<$data.length; i++){
            let $rowItem = $rowDef.clone();
            $rowItem.empty();

            let $columnItem = $columnDef.clone();
            $columnItem.text(i+1);
            $rowItem.append($columnItem);

            if ($data.resource == "beverages"){
                $data[i].incentiveId = $data[i].incentiveDTO.id;
                $data[i].incentiveName = $data[i].incentiveDTO.name;
                $data[i].incentiveType = $data[i].incentiveDTO.incentive_type;
            }

            $props.forEach($property => {
                $columnItem = $columnDef.clone();
                $columnItem.text($data[i][$property]);
                $rowItem.append($columnItem);
            });

            let $actionColumn = '<td><p><a class="btn btn-primary update" data-resource="'+$data.resource+'" ' +
                'data-id="'+$data[i].id+'">Update</a> <a class="btn btn-primary delete" ' +
                'data-resource="'+$data.resource+'" data-id="'+$data[i].id+'">Delete</a> </p></td>';
            $rowItem.append($actionColumn);

            $tableDef.append($rowItem);
        }
    }
    else {
        console.log($data);
    }
}

function getDataFromBackend($resourceUrl, callbackMethod){
    $.ajax({
        url: '/' + $resourceUrl,
        type: 'GET',
        dataType: "json",
        success: function (data, textStatus, jqxhr) {
            console.log("request successful");
            data.status = textStatus;
            data.statusCode = jqxhr.status;
            data.resource = $resourceUrl;
            callbackMethod(data);
        },
        error: function (jqxhr){
            console.log("request failed");
            let data = jqxhr.responseJSON;
            data.status = jqxhr.statusText;
            data.statusCode = jqxhr.status;
            data.resource = $resourceUrl;
            callbackMethod(data);
        }
    });
}