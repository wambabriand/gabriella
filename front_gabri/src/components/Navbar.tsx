

const Navbar = () =>{
    return ( <div className="fixed-top">
       
        <nav className="navbar navbar-expand-lg navbar-dark bg-success  px-3">
          <div className="container-fluid">
            <div className="navbar-nav me-auto">
              <a className="nav-link" href="#">TELEGRAM</a>
              <a className="nav-link" href="#">WHATSAPP</a>
              <a className="nav-link" href="#">CONTENUTI</a>
              <a className="nav-link" href="#">+CONTENUTO</a>
              <a className="nav-link" href="#">MEMBRI</a>
              <a className="nav-link" href="#">+MEMBRO</a>
              <a className="nav-link" href="#">PRODOTTI</a>
            </div>
      
            <span className="navbar-text mx-auto d-none d-lg-block">
              Benvenuto: <strong>briandwamba@yahoo.fr</strong>
            </span>
      
            <div className="navbar-nav ms-auto">
              <a className="nav-link" href="#">PARAMETRI ACCOUNT</a>
              <a className="nav-link" href="#">DISCONNETTI</a>
            </div>
          </div>
        </nav>
      
       
        <div className="container text-center mt-1 pt-1">
          <button className="btn btn-success m-2">Home Telegram</button>
          <button className="btn btn-dark m-2">Login Telegram</button>
          <button className="btn btn-secondary m-2">Autre bouton</button>
        </div>
      </div>
        
      );


 
};



export default Navbar;