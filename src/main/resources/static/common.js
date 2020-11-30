function adjustContentPage() {
    $(".container").removeAttr("align");
    $(".dynamic-content").remove();
    $(".homepage").hide();
    let $dataContent = $(".data-content").clone();
    $dataContent.addClass("dynamic-content");
    return $dataContent;
}

$(document).on("click", ".menu-section .row .btn", function (){
    let $dataContent = adjustContentPage();

    let $dataItem = $(this).attr("data-menu");
    let columns;
    switch ($dataItem) {
       case "incentiveMenu":
           columns = ["#", "ID", "NAME", "TYPE", "ACTION"];
           createTableDefinition("Incentive Management", "Create new incentive",
               $dataContent, columns);
           getDataFromBackend("incentives", populateTable);
           break;
        case "beverageMenu":
            columns = ["#", "ID", "NAME", "MANUFACTURER", "QUANTITY", "PRICE", "INCENTIVE_ID",
                "INCENTIVE_NAME", "INCENTIVE_TYPE", "ACTION"];
            createTableDefinition("Beverage Management", "Create new beverage",
                $dataContent, columns);
            getDataFromBackend("beverages", populateTable);
            break;
        case "orderMenu":
            columns = ["#", "MANUFACTURER", "NAME", "PRICE",
                "QUANTITY", "INCENTIVE_NAME", "SELECTION", "ORDER QUANTITY"];
            createTableDefinition("Beverages For Order", null,
                $dataContent, columns);
            getDataFromBackend("customerOrder", populateTable);
            break;
        case "reportMenu":
            columns = ["#", "ORDER_ID", "REVENUE"];
            createTableDefinition("Report summary", null,
                $dataContent, columns);
            getDataFromBackend("sales-report/summary", populateTable);
            $(".dynamic-content .home-button").remove();
            getDataFromBackend("sales-report/detail", populateDetailReportTable);
            break;
       default:
           console.log("something else");
    }
});

$(document).on("click", ".home-button", function (){
    $(".dynamic-content").remove();
    $(".homepage").show();
});

function createTableDefinition($contentLabel, $createButtonText, $dataContent, $columns) {
    $(".container").append($dataContent);
    $dataContent.find(".content-label").text($contentLabel);
    if ($createButtonText != null){
        $dataContent.find(".create-button").text($createButtonText);
    }
    else {
        $dataContent.find(".create-button").remove();
    }
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

function populateTable($data){
    if ($data.status == "success"){
        let $columnDef = $(".dynamic-content").find("table tbody tr td").clone();
        let $rowDef = $(".dynamic-content").find("table tbody tr").clone();
        let $tableDef = $(".dynamic-content").find("table tbody");
        $tableDef.empty();

        let $props = [];
        if ($data.resource == "incentives"){
            $props = ["id", "name", "incentive_type"];
            $(".dynamic-content .create-button").attr("data-resource", "incentives")
        }
        else if ($data.resource == "beverages"){
            $props = ["id", "name", "manufacturer", "quantity", "price", "incentiveId", "incentiveName", "incentiveType"];
            $(".dynamic-content .create-button").attr("data-resource", "beverages")
        }
        else if ($data.resource == "customerOrder"){
            $props = ["manufacturer", "name", "price", "quantity", "incentiveName"];
        }
        else if ($data.resource == "sales-report/summary"){
            $props = ["customerOrderId", "revenue"];
        }
        else if ($data.resource == "customer_order"){
            $props = ["manufacturer", "name", "price", "incentiveName", "orderAmount"];
        }
        for (let i=0; i<$data.length; i++){
            let $rowItem = $rowDef.clone();
            $rowItem.empty();

            let $columnItem = $columnDef.clone();
            $columnItem.text(i+1);
            $rowItem.append($columnItem);

            if ($data.resource == "beverages"){
                $data[i].incentiveId = $data[i].incentiveDTO != null ? $data[i].incentiveDTO.id : "-";
                $data[i].incentiveName = $data[i].incentiveDTO != null ? $data[i].incentiveDTO.name : "-";
                $data[i].incentiveType = $data[i].incentiveDTO != null ? $data[i].incentiveDTO.incentive_type : "-";
            }
            else if ($data.resource == "customerOrder"){
                $data[i].incentiveName = $data[i].incentiveDTO != null ? $data[i].incentiveDTO.name : "-";
            }
            else if ($data.resource == "customer_order"){
                $data[i].manufacturer = $data[i].beverageDTO.manufacturer;
                $data[i].name = $data[i].beverageDTO.name;
                $data[i].price = $data[i].beverageDTO.price;
                $data[i].incentiveName = $data[i].beverageDTO.incentiveDTO != null ? $data[i].beverageDTO.incentiveDTO.name : "-";
            }

            $props.forEach($property => {
                $columnItem = $columnDef.clone();
                $columnItem.text($data[i][$property]);
                $rowItem.append($columnItem);
            });

            let $actionColumn;
            if ($data.resource == "customerOrder"){
                $actionColumn = '<td><div class="col-xs" align="center"><input type="checkbox" name="selectedBeverage" ' +
                    'value="'+$data[i].id+'"></div></td>';
                $rowItem.append($actionColumn);

                $actionColumn = '<td><div class="col-xs" align="center"><input type="number" name="quantity" min="0" '+
                    'data-id="'+$data[i].id+'" max="'+$data[i].quantity+'" value="0"></div></td>';
                $rowItem.append($actionColumn);
                $(".dynamic-content .order-submit-button").show();
            }
            else if ($data.resource == "incentives" || $data.resource == "beverages"){
                $actionColumn = '<td><p><a class="btn btn-primary update" data-resource="'+$data.resource+'" ' +
                    'data-id="'+$data[i].id+'">Update</a> <a class="btn btn-primary delete" ' +
                    'data-resource="'+$data.resource+'" data-id="'+$data[i].id+'">Delete</a> </p></td>';
                $rowItem.append($actionColumn);
            }

            $tableDef.append($rowItem);
        }
    }
    else {
        console.log($data);
    }
}

function populateDetailReportTable($data){
    $(".dynamic-content").append("<h2>Report broken down to incentive type</h2>");
    let $detailReportTable = $(".dynamic-content .table-responsive").clone();
    $detailReportTable.addClass("detail-report");

    $tableBody = $detailReportTable.find("tbody");
    $tableBody.empty();

    for (let i=0; i<$data.length; i++){
        let $html = '<tr><td>'+(i+1)+'</td><td>'+$data[i].incentiveType+'</td><td>'+$data[i].revenue+'</td></tr>';
        $tableBody.append($html);
    }
    $(".dynamic-content").append($detailReportTable);
    $(".detail-report th:nth-child(2)").text("INCENTIVE_TYPE");
    $(".dynamic-content").append('<p class="btn btn-primary home-button">Home</p>');
}

function getDataFromBackend($resource, callbackMethod){
    let $resourceUrl = $resource;
    if ($resource == "customerOrder"){
        $resourceUrl = "beverages";
    }
    $.ajax({
        url: '/' + $resourceUrl,
        type: 'GET',
        dataType: "json",
        success: function (data, textStatus, jqxhr) {
            console.log("request successful");
            data.status = textStatus;
            data.statusCode = jqxhr.status;
            data.resource = $resource;
            callbackMethod(data);
        },
        error: function (jqxhr){
            console.log("request failed");
            let data = jqxhr.responseJSON;
            data.status = jqxhr.statusText;
            data.statusCode = jqxhr.status;
            data.resource = $resource;
            callbackMethod(data);
        }
    });
}