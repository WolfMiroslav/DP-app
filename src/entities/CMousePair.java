package entities;

public class CMousePair<p, r> 
{
    private CMyPoint p;
    private CMyPoint r;
    
    public CMousePair(CMyPoint p, CMyPoint r)
    {
        this.p = p;
        this.r = r;
    }
    public CMyPoint getP(){ 
    	return p; 
    }
    
    public CMyPoint getR()
    { 
    	return r; 
    }
    
    public void setP(CMyPoint p)
    { 
    	this.p = p; 
    }
    
    public void setR(CMyPoint r)
    { 
    	this.r = r; 
    }
}
