package gui;

import data.MusicFile;
import savesystem.XMLparser;

import java.io.File;
import java.util.HashMap;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.media.*;

public class MusicLibrary extends Application {

	StackPane allContent;
	GridPane mainContent;
	TableView<SongEntry> songsTable;
	
	private void setupMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File");
		MenuItem reloadSongs = new MenuItem("Reload Songs");
		reloadSongs.setOnAction(e -> {
			File input = new File("/Users/HansHalverson/Music/iTunes/iTunes Music Library.xml");
			HashMap<Integer, MusicFile> library = XMLparser.parseXML(input);
			ObservableList<SongEntry> songEntries = FXCollections.observableArrayList();
			for (MusicFile song : library.values()) {
				SongEntry entry = new SongEntry(song);
				songEntries.add(entry);
			}
			songsTable.setItems(songEntries);
		});
		menu.getItems().add(reloadSongs);
		menuBar.getMenus().add(menu);
		menuBar.setUseSystemMenuBar(true);
		allContent.getChildren().add(menuBar);
	}
	
	private void setupGenreButtons() {
		ToggleButton allGenres = new ToggleButton("All");
		allGenres.setId("leftGenreButton");
		allGenres.getStyleClass().add("genreButton");
		allGenres.setSelected(true);
		allGenres.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			((ToggleButton) e.getSource()).setSelected(true);
		});
		ToggleButton electronicGenre = new ToggleButton("Electronic");
		electronicGenre.getStyleClass().add("genreButton");
		electronicGenre.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			((ToggleButton) e.getSource()).setSelected(true);
		});
		ToggleButton hiphopGenre = new ToggleButton("Hip Hop/Rap");
		hiphopGenre.getStyleClass().add("genreButton");
		hiphopGenre.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			((ToggleButton) e.getSource()).setSelected(true);
		});
		ToggleButton jazzGenre = new ToggleButton("Jazz");
		jazzGenre.getStyleClass().add("genreButton");
		jazzGenre.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			((ToggleButton) e.getSource()).setSelected(true);
		});
		ToggleButton rnbGenre = new ToggleButton("R&B/Soul");
		rnbGenre.getStyleClass().add("genreButton");
		rnbGenre.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			((ToggleButton) e.getSource()).setSelected(true);
		});
		ToggleButton rockGenre = new ToggleButton("Rock");
		rockGenre.getStyleClass().add("genreButton");
		rockGenre.setId("rightGenreButton");
		rockGenre.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			((ToggleButton) e.getSource()).setSelected(true);
		});
		ToggleGroup genresGroup = new ToggleGroup();
		genresGroup.selectedToggleProperty().addListener((ov, toggle, newToggle) -> {
			if (newToggle == null) {
				toggle.setSelected(true);
			}
		});
		genresGroup.getToggles().add(allGenres);
		genresGroup.getToggles().add(electronicGenre);
		genresGroup.getToggles().add(hiphopGenre);
		genresGroup.getToggles().add(jazzGenre);
		genresGroup.getToggles().add(rnbGenre);
		genresGroup.getToggles().add(rockGenre);
		HBox genresBox = new HBox();
		genresBox.setAlignment(Pos.CENTER);
		genresBox.getChildren().add(allGenres);
		genresBox.getChildren().add(electronicGenre);
		genresBox.getChildren().add(hiphopGenre);
		genresBox.getChildren().add(jazzGenre);
		genresBox.getChildren().add(rnbGenre);
		genresBox.getChildren().add(rockGenre);
		genresBox.setPadding(new Insets(5));
		mainContent.add(genresBox, 0, 0, 2, 1);
	}
	
	private void setupPlaySkipButtons() {
		HBox controlButtonsBox = new HBox();
		ToggleButton playPauseButton = new ToggleButton();
		playPauseButton.setId("playPauseButton");
		Button skipForwardButton = new Button();
		skipForwardButton.setId("skipForwardButton");
		Button skipBackwardButton = new Button();
		skipBackwardButton.setId("skipBackwardButton");
		controlButtonsBox.getChildren().add(skipBackwardButton);
		controlButtonsBox.getChildren().add(playPauseButton);
		controlButtonsBox.getChildren().add(skipForwardButton);
		controlButtonsBox.setAlignment(Pos.CENTER);
		controlButtonsBox.setSpacing(20);
		controlButtonsBox.setPadding(new Insets(0, 20, 0, 10));
		
		File bloodOnTheLeavesFile = new File("07%20Blood%20On%20The%20Leaves.m4a");
		Media bloodOnTheLeaves = new Media("file:///" + bloodOnTheLeavesFile.getAbsolutePath());
		MediaPlayer songPlayer = new MediaPlayer(bloodOnTheLeaves);
		playPauseButton.setOnAction(e -> {
			if (((ToggleButton) e.getSource()).isSelected()) {
				songPlayer.play();
			} else {
				songPlayer.pause();
			}
		});
		
		GridPane albumToolsPane = new GridPane();
		Slider volumeSlider = new Slider();
		volumeSlider.setId("volumeSlider");
		TextField searchBar = new TextField();
		albumToolsPane.add(controlButtonsBox, 1, 0);
		albumToolsPane.add(volumeSlider, 0, 0);
		albumToolsPane.add(searchBar, 2, 0);
		albumToolsPane.setHgap(20);
		albumToolsPane.setPadding(new Insets(5, 5, 5, 5));
		albumToolsPane.setHgrow(volumeSlider, Priority.SOMETIMES);
		albumToolsPane.setAlignment(Pos.CENTER);
		mainContent.add(albumToolsPane, 3, 0);
	}
	
	private void setupAlbumSelection() {
		ListView subgenresList = new ListView(FXCollections.observableArrayList("Post-Rock", "Shoegaze", "Indie Rock", "Progressive Rock", "Psychedelic Rock", "Neo-Psychedelia", "Noise Rock", "Space Rock", "Garage Rock", "Alternative Rock", "Abstract Hip-Hop", "West Coast Hip Hop", "House", "Techno", "Dubstep", "Trance", "Future Garage", "Ambient", "Dark Ambient", "Synthpop", "Chillwave", "Trip Hop", "Instrumental Hip Hop", "Plunderphonics", "Trap", "Singer-Songwriter", "Folk", "Indie Folk", "Folk Rock", "Acoustic", "Lo-Fi"));
		subgenresList.setPrefWidth(175);
		subgenresList.setId("subgenresList");
		
		TreeItem artistAlbumRoot = new TreeItem();
		TreeView artistAlbumTree = new TreeView(artistAlbumRoot);
		artistAlbumTree.setId("artistAlbumTree");
		artistAlbumTree.setShowRoot(false);
		artistAlbumTree.setPrefWidth(200);
		artistAlbumTree.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.LEFT) {
				subgenresList.requestFocus();
			}
		});
		
		artistAlbumRoot.getChildren().add(new TreeItem("Swans"));
		artistAlbumRoot.getChildren().add(new TreeItem("Burial"));
		TreeItem kendrickItem = new TreeItem("Kendrick Lamar");
		kendrickItem.getChildren().addAll(new TreeItem("Section 80"), new TreeItem("Good Kid, M.A.A.D. City"), new TreeItem("To Pimp A Butterfly"));
		artistAlbumRoot.getChildren().add(kendrickItem);
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		artistAlbumRoot.getChildren().add(new TreeItem("Sigur Ros"));
		
		mainContent.add(subgenresList, 0, 1);
		mainContent.add(artistAlbumTree, 1, 1);
		mainContent.setVgrow(subgenresList, Priority.ALWAYS);
		mainContent.setVgrow(artistAlbumTree, Priority.ALWAYS);
	}
	
	private void setupAlbumContent() {
		songsTable = new TableView();
		TableColumn<SongEntry, String> songTitleCol = new TableColumn("Name");
		TableColumn<SongEntry, String> songLengthCol = new TableColumn("Time");
		TableColumn<SongEntry, java.lang.Number> songPlaysCol = new TableColumn("Plays");
		songTitleCol.setPrefWidth(250);
		songLengthCol.setPrefWidth(40);
		songPlaysCol.setPrefWidth(40);
		songsTable.setEditable(false);
		songTitleCol.setCellValueFactory(cell -> {
			return cell.getValue().getName();
		});
		songLengthCol.setCellValueFactory(cell -> {
			return cell.getValue().getTime();
		});
		songPlaysCol.setCellValueFactory(cell -> {
			return cell.getValue().getPlays();
		});
		songsTable.getColumns().addAll(songTitleCol, songLengthCol, songPlaysCol);
		songsTable.setId("songsTable");
		songsTable.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				SongEntry currSong = songsTable.getSelectionModel().getSelectedItem();
				new MediaPlayer(new Media(currSong.getFile())).play();
			}
		});
		GridPane vPane = new GridPane();
		vPane.setId("currentTrackRegion");
		vPane.setAlignment(Pos.TOP_CENTER);
		File albumImg = new File("yeezus.jpg");
		ImageView albumArt = new ImageView("file:///" + albumImg.getAbsolutePath());
		albumArt.setPreserveRatio(true);
		albumArt.setFitHeight(500);
		albumArt.setFitWidth(500);
		Text songTitle = new Text("Blood on the Leaves");
		songTitle.setId("songTitleText");
		Text artistAlbum = new Text("Kanye West - Yeezus");
		artistAlbum.setId("artistAlbumText");
		GridPane songLengthPane = new GridPane();
		Text currentTimeText = new Text("0:00");
		currentTimeText.setId("currentTimeText");
		Text emptyText = new Text();
		Text totalTimeText = new Text("4:33");
		totalTimeText.setId("totalTimeText");
		songLengthPane.add(currentTimeText, 0, 0);
		songLengthPane.add(emptyText, 1, 0);
		songLengthPane.add(totalTimeText, 2, 0);
		songLengthPane.setHgrow(emptyText, Priority.ALWAYS);
		Slider songScroller = new Slider(0, 4.33, 0);
		songScroller.setId("songScroller");
		songLengthPane.add(songScroller, 0, 1, 3, 1);
		songLengthPane.setPadding(new Insets(0, 10, 15, 10));
		vPane.add(albumArt, 0, 0);
		vPane.add(songTitle, 0, 1);
		vPane.add(songLengthPane, 0, 2);
		vPane.add(artistAlbum, 0, 3);
		
		mainContent.add(songsTable, 2, 1);
		mainContent.add(vPane, 3, 1);
		mainContent.setHgrow(songsTable, Priority.SOMETIMES);
		vPane.setVgrow(songTitle, Priority.SOMETIMES);
		vPane.setHalignment(songTitle, HPos.CENTER);
		vPane.setValignment(songTitle, VPos.BOTTOM);
		vPane.setVgrow(artistAlbum, Priority.SOMETIMES);
		vPane.setHalignment(artistAlbum, HPos.CENTER);
		vPane.setValignment(artistAlbum, VPos.TOP);
	}
	
	private void setupUpperMiddlePane() {
		Region upperMiddlePane = new Region();
		upperMiddlePane.setId("upperMiddlePane");
		mainContent.add(upperMiddlePane, 2, 0);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		allContent = new StackPane();
		mainContent = new GridPane();
		allContent.getChildren().add(mainContent);
		setupMenuBar();
		setupGenreButtons();
		setupPlaySkipButtons();
		setupAlbumSelection();
		setupAlbumContent();
		setupUpperMiddlePane();
		
		Scene scene = new Scene(allContent, 2000, 800);
		File styleFile = new File("styles/main_theme.css");
		scene.getStylesheets().add("file:///" + styleFile.getAbsolutePath());
		stage.setScene(scene);
		stage.setTitle("Music Library");
		stage.show();
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
