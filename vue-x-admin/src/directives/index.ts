const directives:any=import.meta.glob('./modules/*.ts',{eager:true})
export default{
    install(app:any){
        Object.keys(directives).forEach(key=>{
            const name=key.replace(/\.\/modules\/(.*)\.ts/,'$1')
            app.directive(name,directives[key].default)
        })
    }
}