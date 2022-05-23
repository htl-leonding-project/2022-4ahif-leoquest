# Diplomarbeit "Online Survey"

- ACHTUNG: Wir verwenden main anstelle master als Hauptbranch


- TODO
  - Datenmodell
  - Einrichten von Youtrack  
    - Backlog erstellen (die grobe Funktionalität definieren)
  - Gliederung für schriftliche Ausarbeitung erstellen

- bereits bestehende Projekte
  - https://github.com/htl-leonding-project/questionz
  - https://github.com/htl-leonding-project/leo-survey  
  

- Umstellen von Hauptbranch `master` auf Hauptbranch `main`
  - die lokale Git-Installation so konfigurieren, dass `main` der Hauptbranch ist
    ```
    git config --global init.defaultBranch main
    ```
    
  - den aktuellen `master` - Branch umbennen
    ```
    git branch -m main
    ```
    
  - push the new local `master`-branch and reset the upstream branch
    ```
    git push origin -u main
    ```
    
  - delete the old `master` - branch  
    ```
    git push origin --delete master
    ```
  
  - https://linuxize.com/post/how-to-rename-local-and-remote-git-branch/  

