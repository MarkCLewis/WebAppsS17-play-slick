/**
 * Functions for the handling of unemployment pages. 
 */

function setup() {
	$("#selector").change(selectPull);
}

function selectPull() {
	$("#table").load($("#selector").val())
}