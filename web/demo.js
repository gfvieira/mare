// chart features to add
// - load data to BDB Server
// - Point select feature to return list of surfaces in area
var map;
var clsList;

function contains(a, obj) {
    for (var i = 0; i < a.length; i++) {
        if (a[i] === obj) {
            return true;
        }
    }
    return false;
}
;

brazil = {
    init: function () {

        // Load the map
        map = new oscar.Map('map', {
            controls: [new oscar.Control.PanZoomBar, new OpenLayers.Control.MousePosition, new OpenLayers.Control.ScaleLine]
        });

        // If you are working through a network proxy, use this line
        //OpenLayers.ProxyHost = 'surfer.caris.priv:80';

        // Sample World layer
        brazil.wmsLayer = new OpenLayers.Layer.OSM({wrapDateLine: true, isBaseLayer: true});
        //brazil.wmsLayer = new OpenLayers.Layer.WMS('World','http://localhost:8080/spatialfusionserver/services/ows/wms/test',{layers:'Mundo',bgcolor:'0xa0a0b6'});
        map.addLayers([brazil.wmsLayer]);
        map.setCenter(new OpenLayers.LonLat(0, 10), 2);

        // Colour style for ENC Chart boundaries
        var encstyle = new OpenLayers.Style({fillColor: '#dd0000', fillOpacity: 0.2, strokeColor: '#dd0000', strokeWidth: 0.5});
        var encselect = new OpenLayers.Style({fillColor: '#00ffff', strokeColor: '#00ffff'});
        brazil.SelStyle = {fillColor: '#0000cc', strokeColor: '#0000cc'};
        brazil.ENCStyle = new OpenLayers.StyleMap({'default': encstyle, 'select': encselect});
        
        brazil.charts = (function () {
            var json;
            $.ajax({
                type: 'GET',
                url: '/chm/Mare.jsp',
                async: false,
                global: false,
                success: function (data) {
                    json = data;
                },
                error: function () {
                    alert("Error loading chart");
                }
            });
            return json;
        })();// inserir 
        brazil.selectedFeats = new Array();
        // Link for tide gauges json file
        brazil.tidegauges = "tide_gauges.json";
        // Load the list of usages
        var opts = document.getElementById('usages');
        opts.options.length = 0;
        opts.options[0] = new Option("(no selection)", 0);
        Usagevals = {1: "Overview", 2: "General", 3: "Costa", 4: "Approach", 5: "Harbour", 6: "Berthing"};
        UsageRanges = {0: 999999999, 1: 1500000, 2: 600000, 3: 150000, 4: 50000, 5: 5000, 6: 0};
        for (var i in Usagevals) {
            opts.options[opts.options.length] = new Option(Usagevals[i], i);
        }

        // Create new ENCMap Layer
        brazil.ENCMap = new OpenLayers.Layer.Vector("Cartas",
                {strategies: [new OpenLayers.Strategy.Fixed()],
                    styleMap: brazil.ENCStyle,
                    protocol: new OpenLayers.Protocol.HTTP({
                        url: brazil.charts,
                        format: new OpenLayers.Format.GeoJSON()
                    })
                });
        // Create new Tide Gauges Layer
        brazil.tidestation = new OpenLayers.Layer.Vector("Tides",
                {strategies: [new OpenLayers.Strategy.Fixed()],
                    styleMap: brazil.ENCStyle,
                    protocol: new OpenLayers.Protocol.HTTP({
                        url: brazil.tidegauges,
                        format: new OpenLayers.Format.GeoJSON()
                    })
                });

        // Create Selection layer
        // Used when you want to click on a polygon
        brazil.SelVectors = new OpenLayers.Layer.Vector("Selected", {
            style: brazil.SelStyle
        });

        // Add layers to the map
        map.addLayers([brazil.ENCMap, brazil.SelVectors]);
        // Add Tide gauges layer to the map
        map.addLayers([brazil.tidestation, brazil.SelVectors]);

        // Create events when a user clicks on the map
        brazil.ENCMap.events.on({'featureselected': brazil.featureSelect, 'featureunselected': brazil.featureSelect});
        // Create events when a user clicks on the map
        brazil.tidestation.events.on({'featureselected': brazil.featureSelect, 'featureunselected': brazil.featureSelect});
        // Activate highlighting polygons
        brazil.HLFeat = new OpenLayers.Control.SelectFeature(brazil.ENCMap, {
            hover: true,
            highlightOnly: true,
            renderIntent: "temporary"
        });
        map.addControl(brazil.HLFeat);
        brazil.HLFeat.activate();

        // Wait one second to ensure all code has been downloaded.
        setTimeout(brazil.activateEvents, 2000);
    },
    activateEvents: function () {
        jQuery('#searchCharts').click(brazil.search);
    },
    featureSelect: function (feature) {
        feat = feature.feature.attributes;
        prodkey = feat.Numero;
        objinfo = jQuery('#objInfo');
        objinfo.html("");
        if (!(prodkey in brazil.selectedFeats)) {
            brazil.selectedFeats[prodkey] = feat;
            brazil.SelVectors.addFeatures([feature]);

            objinfo.html("<br /><table>" +
                    "<tr><th>Carta:</th><td>" + feat.Numero + "</td></tr>" +
                    "<tr><th>T�tulo:</th><td>" + feat.Titulo + "</td></tr>" +
                    "<tr><th>Edi��o:</th><td>" + feat.Ediciao + "</td></tr>" +
                    "<tr><th>Escala:</th><td> 1:" + feat.Escala + "</td></tr>" +
                    "<tr><th>Ano:</th><td>" + feat.Ano + "</td></tr>" +
                    "<tr><th>Raster:</th><td>" + feat.RNC + "</td></tr>" +
                    "<tr><th>Geotiff:</th><td>" + feat.TIFF + "</td></tr>" +
                    "<tr><th>Papel:</th><td>" + feat.PAPEL + "</td></tr>" +
                    "</table>");
        } else {
            delete brazil.selectedFeats[prodkey];
            brazil.SelVectors.removeFeatures([feature]);
        }

    },
    search: function () {
        var begYr = ((jQuery("#beginDate").val()) ? jQuery('#beginDate').val() : 0);
        var endYr = ((jQuery('#endDate').val()) ? jQuery('#endDate').val() : 99999);
        var usg = ((jQuery('#usages').val()) ? jQuery('#usages').val() : 0);
        for (var f in brazil.ENCMap.features) {
            var hid = false;
            brazil.ENCMap.features[f].style = null;
            ano = brazil.ENCMap.features[f].attributes.Ano;
            esc = brazil.ENCMap.features[f].attributes.Escala;

            if (ano < begYr ||
                    ano > endYr) {
                brazil.ENCMap.features[f].style = {display: 'none'};
                hid = true;
            }
            if (usg > 0) {
                if (!(esc > UsageRanges[usg] &&
                        esc < UsageRanges[usg - 1])) {
                    brazil.ENCMap.features[f].style = {display: 'none'};
                    hid = true;
                }
            }
        }
        brazil.ENCMap.redraw();
    }

}
