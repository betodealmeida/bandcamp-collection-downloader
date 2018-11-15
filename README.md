# Bandcamp collection downloader

A command-line tool written in Kotlin to automatically download all albums of a Bandcamp collection.


```
Usage: <main class> [-h] [-c=<pathToCookiesFile>] [-d=<pathToDownloadFolder>] [-f=<audioFormat>] <bandcampUser>
      <bandcampUser>   The bandcamp user account from which all albums must be downloaded.
  -c, --cookies-file=<pathToCookiesFile>
                       A JSON file with valid bandcamp credential cookies.
                       "Cookie Quick Manager" can be used to obtain this file after logging into bandcamp.
                       (visit https://addons.mozilla.org/en-US/firefox/addon/cookie-quick-manager/).
                       If no cookies file is provided, cookies from the local Firefox installation are used (Linux only).
  -d, --download-folder=<pathToDownloadFolder>
                       The folder in which downloaded albums must be extracted.
                       The following structure is considered: <pathToDownloadFolder>/<artist>/<year> - <album>.
                       (default: current folder)
  -f, --audio-format=<audioFormat>
                       The chosen audio format of the files to download (default: vorbis).
                       Possible values: flac, wav, aac-hi, mp3-320, aiff-lossless, vorbis, mp3-v0, alac.
  -h, --help           Display this help message.
```  

## Bandcamp authentication 

This tool does not manage authentication with Bandcamp servers, as they require a valid token from Google Captcha.
Hence, authentication must first be achieved using Firefox. Then there are two possibilities:
- If running a Linux system (and if the `--cookies-file` parameter is not used), then the tool will automatically find the required cookies in the Firefox profile folder of the system user.
- Else, Bandcamp cookies must be exported in JSON using the Firefox Addon [Cookie Quick Manager](https://addons.mozilla.org/en-US/firefox/addon/cookie-quick-manager/). 
This JSON file can then be used using the parameter `--cookies-file`:

## Installation

After each commit, the continuous integration (CI) builds a new standalone (ie. that includes all dependencies) executable jar that can be used as is.
The last produced jar can always be found [at this link](https://framagit.org/Gwendal/bandcamp-collection-downloader/-/jobs/artifacts/master/raw/build/libs/bandcamp-collection-downloader.jar?job=build).

## Usage

Example of command:

```dtd
$ java -jar bandcamp-collection-downloader.jar --cookies-file=cookies.json --download-folder=~/Music myBandcampIdentifier
```

This will download all albums ever bought by the Bandcamp account *myBandcampIdentifier*, 
and extract the music in the folder `~/Music`, 
The tool always creates one folder per artist, each containing one folder per album (with the format `<year> - <album name>`).

## Compiling

Compilation is handled by Gradle.
A task `fatJar` is available to build a standalone executable jar with all dependencies:  

```
$ git clone https://framagit.org/Gwendal/bandcamp-collection-downloader.git
$ cd bandcamp-collection-downloader
$ gradle fatjar
```

The resulting binary can then be found in `build/libs/bandcamp-collection-downloader.jar`.

## Dependencies
 
 This tool relies on the following libraries:
 - [jsoup](https://jsoup.org/) to get and parse HTML,
 - [zt-zip](https://github.com/zeroturnaround/zt-zip) to unzip downloaded albums,
 - [picocli](https://picocli.info/) to provide a fancy CLI,
 - [Gson](https://github.com/google/gson) to parse JSON.